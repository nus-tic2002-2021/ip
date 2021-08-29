package com.lockarhythm.query;

import com.lockarhythm.query.addlist.AddListResponder;
import com.lockarhythm.query.exit.ExitResponder;
import com.lockarhythm.query.markasdone.MarkAsDoneResponder;
import com.lockarhythm.tasks.TaskList;

/** SimpleQueryInterpreter finds the first QueryInterpreter that responds a non-null result. */
public class SimpleQueryInterpreter implements QueryInterpreter {
  static QueryInterpreter[] interpreters;

  public SimpleQueryInterpreter(TaskList list) {
    QueryInterpreter[] res = {
      new ExitResponder(), new MarkAsDoneResponder(list), new AddListResponder(list),
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
