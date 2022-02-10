package com.lockarhythm.query.find;

import com.lockarhythm.query.RegexQueryInterpreter;
import com.lockarhythm.query.Result;
import com.lockarhythm.tasks.TaskList;

public class FindResponder extends RegexQueryInterpreter {
  private TaskList list;

  public FindResponder(TaskList list) {
    this.list = list;
  }

  protected String commandRegex() {
    return "^find (.*)$";
  }

  public Result onMatch(String[] groups) {
    String query = groups[1];

    TaskList filteredList = list.find(query);

    if (filteredList.size() == 0) {
      return new Result(
          String.format("I could not find any tasks matching your query '%s'", query));
    }
    return new Result(
        String.format(
            "Ok! I have found these tasks for your query '%s'!\n%s", query, filteredList));
  }
}
