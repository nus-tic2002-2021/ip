package com.lockarhythm.tasks;

import java.io.Serializable;

public abstract class Task implements Serializable {
  private static transient String DEFAULT_TASK_DATE_VALUE = "3000-12-31T23:59:59";
  private static transient TaskDate DEFAULT_TASK_DATE = new TaskDate(DEFAULT_TASK_DATE_VALUE);

  private String description;
  private boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public void toggleDone() {
    this.isDone = !this.isDone;
  }

  public void setDone(boolean isDone) {
    this.isDone = isDone;
  }

  public String getDescription() {
    return description;
  }

  public boolean isDone() {
    return isDone;
  }

  public TaskDate getTaskDate() {
    return DEFAULT_TASK_DATE;
  }

  private String getDoneIcon() {
    return isDone ? "X" : " ";
  }

  protected abstract String getTaskTypeIcon();

  @Override
  public String toString() {
    return String.format("[%s][%s] %s", getTaskTypeIcon(), getDoneIcon(), description);
  }
}
