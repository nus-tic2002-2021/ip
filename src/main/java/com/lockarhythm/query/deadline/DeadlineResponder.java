package com.lockarhythm.query.deadline;

import com.lockarhythm.query.AddedTaskResult;
import com.lockarhythm.query.RegexQueryInterpreter;
import com.lockarhythm.query.Result;
import com.lockarhythm.tasks.Task;
import com.lockarhythm.tasks.TaskList;

public class DeadlineResponder extends RegexQueryInterpreter {
  private TaskList list;

  public DeadlineResponder(TaskList list) {
    this.list = list;
  }

  protected String commandRegex() {
    return "^deadline (.+) \\/by (.+)";
  }

  public Result onMatch(String[] groups) {
    try {
      Task task = list.addDeadlineTask(groups[1], groups[2]);
      return new AddedTaskResult(task, list.size());
    } catch (java.time.format.DateTimeParseException e) {
      return new Result(
          String.format("Please give me a valid LocalDate pattern: %s", e.getMessage()));
    }
  }
}
