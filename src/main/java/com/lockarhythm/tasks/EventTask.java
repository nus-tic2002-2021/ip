package com.lockarhythm.tasks;

final class EventTask extends Task {
  private TaskDate at;
  private String _type = "EVENT";

  public EventTask(String description, String at) {
    super(description);
    this.at = new TaskDate(at);
  }

  @Override
  public TaskDate getTaskDate() {
    return at;
  }

  @Override
  protected String getTaskTypeIcon() {
    return "E";
  }

  @Override
  public String toString() {
    return String.format("%s (at: %s)", super.toString(), at);
  }
}
