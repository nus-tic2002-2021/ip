package com.lockarhythm.query;

import com.lockarhythm.tasks.Task;

public class DeletedTaskResult extends Result {
  private Task task;
  private int size; // new size after deletion

  public DeletedTaskResult(Task task, int size) {
    super(task.toString());
    this.task = task;
    this.size = size;
  }

  @Override
  public String getText() {
    return String.format(
        "Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.", task, size);
  }
}
