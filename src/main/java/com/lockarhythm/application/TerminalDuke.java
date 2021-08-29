package com.lockarhythm.application;

import com.lockarhythm.query.SimpleQueryInterpreter;
import com.lockarhythm.tasks.TaskList;
import com.lockarhythm.ui.TerminalUI;

public class TerminalDuke extends Application {
  public static void main(String[] args) {
    run(new TerminalUI(), new SimpleQueryInterpreter(new TaskList()));
  }
}
