package com.lockarhythm.tasks;

final class EventTask extends Task {
  private String at;

  public EventTask(String description, String at) {
    super(description);
    this.at = at;
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
