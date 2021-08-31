package com.lockarhythm.query.deadline;

import com.lockarhythm.query.RegexQueryInterpreter;
import com.lockarhythm.query.Result;
import com.lockarhythm.query.AddedTaskResult;
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
    Task task = list.addDeadlineTask(groups[1], groups[2]);
    return new AddedTaskResult(task, list.size());
  }
}
