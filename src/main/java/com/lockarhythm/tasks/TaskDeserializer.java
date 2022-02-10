package com.lockarhythm.tasks;

import com.google.gson.*;
import com.lockarhythm.storage.GsonLocalDate;
import com.lockarhythm.storage.GsonLocalDateTime;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/** Technique credit to https://www.baeldung.com/gson-list */
public class TaskDeserializer implements JsonDeserializer<Task> {
  private String taskTypeElementName;
  private Gson gson;
  private Map<String, Class<? extends Task>> taskTypeRegistry;

  public TaskDeserializer(String taskTypeElementName) {
    this.gson =
        new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTime())
            .registerTypeAdapter(LocalDate.class, new GsonLocalDate())
            .create();

    this.taskTypeElementName = taskTypeElementName;
    this.taskTypeRegistry = new HashMap<>();

    this.registerTaskType("DEADLINE", DeadlineTask.class);
    this.registerTaskType("TODO", TodoTask.class);
    this.registerTaskType("EVENT", EventTask.class);
  }

  public void registerTaskType(String taskTypeName, Class<? extends Task> taskType) {
    taskTypeRegistry.put(taskTypeName, taskType);
  }

  public Task deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
    JsonObject taskObject = json.getAsJsonObject();
    JsonElement taskTypeElement = taskObject.get(taskTypeElementName);

    Class<? extends Task> taskType = taskTypeRegistry.get(taskTypeElement.getAsString());
    return gson.fromJson(taskObject, taskType);
  }
}
