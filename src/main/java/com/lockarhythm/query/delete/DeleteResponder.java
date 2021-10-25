package com.lockarhythm.query.delete;

import com.lockarhythm.query.DeletedTaskResult;
import com.lockarhythm.query.RegexQueryInterpreter;
import com.lockarhythm.query.Result;
import com.lockarhythm.tasks.Task;
import com.lockarhythm.tasks.TaskList;

public class DeleteResponder extends RegexQueryInterpreter {
  private TaskList list;

  public DeleteResponder(TaskList list) {
    this.list = list;
  }

  protected String commandRegex() {
    return "^delete (\\d+)";
  }

  public Result onMatch(String[] groups) {
    int index = Integer.parseInt(groups[1]);
    try {
      Task task = list.deleteTask(index - 1);
      return new DeletedTaskResult(task, list.size());
    } catch (IndexOutOfBoundsException e) {
      return new Result(String.format("Item %d is not on the list. I cannot delete it!", index));
    }
  }
}
