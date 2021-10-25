package com.lockarhythm.query.markasdone;

import com.lockarhythm.query.RegexQueryInterpreter;
import com.lockarhythm.query.Result;
import com.lockarhythm.tasks.Task;
import com.lockarhythm.tasks.TaskList;

public class MarkAsDoneResponder extends RegexQueryInterpreter {
  private TaskList list;

  public MarkAsDoneResponder(TaskList list) {
    this.list = list;
  }

  protected String commandRegex() {
    return "^done (\\d+)";
  }

  public Result onMatch(String[] groups) {
    int i = Integer.parseInt(groups[1]);
    try {
      Task task = list.markAsDone(i - 1);
      return new Result("Nice! I've marked this task as done:\n\t" + task.toString());
    } catch (IndexOutOfBoundsException e) {
      return new Result(String.format("Item %d is not on the list. I cannot mark it as done!", i));
    }
  }
}
