package com.lockarhythm.application;

import com.lockarhythm.query.DukeException;
import com.lockarhythm.query.QueryInterpreter;
import com.lockarhythm.query.Result;
import com.lockarhythm.ui.UI;

abstract class Application {
  static String logo =
      " \t____        _        \n"
          + "\t|  _ \\ _   _| | _____ \n"
          + "\t| | | | | | | |/ / _ \\\n"
          + "\t| |_| | |_| |   <  __/\n"
          + "\t|____/ \\__,_|_|\\_\\___|\n";

  public static void run(UI ui, QueryInterpreter q) {
    Result result;

    ui.print("Hello I'm\n" + logo, "What can I do for you?");

    while (ui.hasNext()) {
      try {
        result = q.interpret(ui.nextLine());
        ui.print(result);
        if (result.shouldExit()) {
          break;
        }
      } catch (DukeException e) {
        ui.print("Sorry, I don't understand that yet!");
      }
    }
  }
}
