package com.lockarhythm.query;

import com.lockarhythm.tasks.Task;

public class AddedTaskResult extends Result {
  private Task task;
  private int size; // the max size on add

  public AddedTaskResult(Task task, int size) {
    super(task.toString());
    this.task = task;
    this.size = size;
  }

  @Override
  public String getText() {
    return String.format(
        "Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.", task, size);
  }
}
