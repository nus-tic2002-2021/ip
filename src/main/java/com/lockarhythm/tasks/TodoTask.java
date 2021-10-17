package com.lockarhythm.tasks;

public final class TodoTask extends Task {
  private String _type = "TODO";

  public TodoTask(String description) {
    super(description);
  }

  @Override
  protected String getTaskTypeIcon() {
    return "T";
  }
}
