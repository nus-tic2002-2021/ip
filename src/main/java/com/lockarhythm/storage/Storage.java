package com.lockarhythm.storage;


import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.LocalDate;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

import com.lockarhythm.tasks.TaskDeserializer;
import com.lockarhythm.tasks.Task;

import java.util.ArrayList;

public class Storage<T> {
  private String filePath;

  private ArrayList<T> list;

  private Gson gson;

  public Storage(String filePath) {
    this.filePath = filePath;

    gson = new GsonBuilder()
      .registerTypeAdapter(Task.class, new TaskDeserializer("_type"))
      .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTime())
      .registerTypeAdapter(LocalDate.class, new GsonLocalDate())
      .setPrettyPrinting()
      .create();
  }

  public void registerList(ArrayList<T> list) {
    this.list = list;
  }

  public ArrayList<T> load(Class<T> type) {
    String content;
    try {
      content = Files.readString(Path.of(filePath), StandardCharsets.UTF_8);

      Type typeOfT = TypeToken.getParameterized(ArrayList.class, type).getType();
      ArrayList<T> deserialized = gson.fromJson(content, new TypeToken<ArrayList<Task>>(){}.getType());
      if (deserialized == null) {
        return new ArrayList<T>();
      }
      return deserialized;
    } catch (IOException e) {
      return new ArrayList<T>();
    }
  }

  public void overwrite() throws IOException {
    FileOutputStream fo = new FileOutputStream(filePath);
    String js = gson.toJson(list);
    fo.write(js.getBytes());
  }
}
