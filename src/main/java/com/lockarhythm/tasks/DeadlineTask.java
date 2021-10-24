package com.lockarhythm.tasks;

final class DeadlineTask extends Task {
  private TaskDate by;
  private String _type = "DEADLINE";

  public DeadlineTask(String description, String by) {
    super(description);
    this.by = new TaskDate(by);
  }

  @Override
  public TaskDate getTaskDate() {
    return by;
  }

  @Override
  protected String getTaskTypeIcon() {
    return "D";
  }

  @Override
  public String toString() {
    return String.format("%s (by: %s)", super.toString(), by);
  }
}
