package com.lockarhythm.query.todo;

import com.lockarhythm.query.AddedTaskResult;
import com.lockarhythm.query.RegexQueryInterpreter;
import com.lockarhythm.query.Result;
import com.lockarhythm.tasks.Task;
import com.lockarhythm.tasks.TaskList;

public class TodoResponder extends RegexQueryInterpreter {
  private TaskList list;

  public TodoResponder(TaskList list) {
    this.list = list;
  }

  protected String commandRegex() {
    return "^todo (.+)";
  }

  public Result onMatch(String[] groups) {
    Task task = list.addTodoTask(groups[1]);
    return new AddedTaskResult(task, list.size());
  }
}
