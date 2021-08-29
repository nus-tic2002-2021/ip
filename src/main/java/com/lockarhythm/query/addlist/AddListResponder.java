package com.lockarhythm.query.addlist;

import com.lockarhythm.query.QueryInterpreter;
import com.lockarhythm.query.Result;
import com.lockarhythm.tasks.TaskList;

public class AddListResponder implements QueryInterpreter {
  private TaskList list;

  public AddListResponder(TaskList list) {
    this.list = list;
  }

  public Result interpret(String query) {
    if (query.equals("list")) {
      return new Result("Here are the tasks in your list:\n" + list.toString());
    }
    // by default, adds the given query.
    list.add(query);
    return new Result("added: " + query);
  }
}
