package com.lockarhythm.tasks;

public final class TodoTask extends Task {
  public TodoTask(String description) {
    super(description);
  }

  @Override
  protected String getTaskTypeIcon() {
    return "T";
  }
}
