package com.lockarhythm.query.echo;

import com.lockarhythm.query.QueryInterpreter;
import com.lockarhythm.query.Result;

public class EchoResponder implements QueryInterpreter {
  public Result interpret(String query) {
    return new Result(query);
  }
}
