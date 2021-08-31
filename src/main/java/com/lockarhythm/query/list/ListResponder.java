package com.lockarhythm.query.list;

import com.lockarhythm.query.QueryInterpreter;
import com.lockarhythm.query.Result;
import com.lockarhythm.tasks.Task;
import com.lockarhythm.tasks.TaskList;

public final class ListResponder implements QueryInterpreter {
  private TaskList list;

  public ListResponder(TaskList list) {
    this.list = list;
  }

  public Result interpret(String query) {
    if (query.equals("list")) {
      return new Result("Here are the tasks in your list:\n" + list.toString());
    }
    return null;
  }
}
