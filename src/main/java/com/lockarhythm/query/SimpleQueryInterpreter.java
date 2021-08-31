package com.lockarhythm.query;

import com.lockarhythm.query.list.ListResponder;
import com.lockarhythm.query.deadline.DeadlineResponder;
import com.lockarhythm.query.event.EventResponder;
import com.lockarhythm.query.exit.ExitResponder;
import com.lockarhythm.query.markasdone.MarkAsDoneResponder;
import com.lockarhythm.query.todo.TodoResponder;
import com.lockarhythm.tasks.TaskList;

/** SimpleQueryInterpreter finds the first QueryInterpreter that responds a non-null result. */
public final class SimpleQueryInterpreter implements QueryInterpreter {
  static QueryInterpreter[] interpreters;

  public SimpleQueryInterpreter(TaskList list) {
    QueryInterpreter[] res = {
      new ExitResponder(),
      new MarkAsDoneResponder(list),
      new TodoResponder(list),
      new ListResponder(list),
      new DeadlineResponder(list),
      new EventResponder(list),
    };
    interpreters = res;
  }

  public Result interpret(String query) {
    Result res = null;
    for (QueryInterpreter interpreter : interpreters) {
      res = interpreter.interpret(query);
      if (res != null) {
        return res;
      }
    }
    return res;
  }
}
