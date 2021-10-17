package com.lockarhythm.storage;


import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

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

  public Storage(String filePath) {
    this.filePath = filePath;
  }

  public void registerList(ArrayList<T> list) {
    this.list = list;
  }

  public ArrayList<T> load(Class<T> type) {
    String content;
    try {
      content = Files.readString(Path.of(filePath), StandardCharsets.UTF_8);

      TaskDeserializer deserializer = new TaskDeserializer("_type");
      Gson gson = new GsonBuilder()
        .registerTypeAdapter(Task.class, deserializer)
        .create();
      Type typeOfT = TypeToken.getParameterized(ArrayList.class, type).getType();
      return gson.fromJson(content, new TypeToken<ArrayList<Task>>(){}.getType());
    } catch (IOException e) {
      return new ArrayList<T>();
    }
  }

  public void overwrite() throws IOException {
    FileOutputStream fo = new FileOutputStream(filePath);

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    String js = gson.toJson(list);
    fo.write(js.getBytes());
  }
}
