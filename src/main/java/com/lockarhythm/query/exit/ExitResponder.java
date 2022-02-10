package com.lockarhythm.query.exit;

import com.lockarhythm.query.QueryInterpreter;
import com.lockarhythm.query.Result;

public class ExitResponder implements QueryInterpreter {
  public Result interpret(String query) {
    if (query.equals("bye")) {
      return new Result("Bye. Hope to see you again soon!", true);
    }
    return null;
  }
}
