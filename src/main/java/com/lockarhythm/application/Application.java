package com.lockarhythm.application;

import com.lockarhythm.query.DukeException;
import com.lockarhythm.query.QueryInterpreter;
import com.lockarhythm.query.Result;
import com.lockarhythm.storage.Storage;
import com.lockarhythm.ui.UI;
import java.io.IOException;

/**
 * Application contains the common logic of all Duke Applications (GUI, TUI).
 *
 * <p>The application architecture is heavily inspired by the Ports & Adapters architecture.
 */
abstract class Application {
  static String logo =
      " \t____        _        \n"
          + "\t|  _ \\ _   _| | _____ \n"
          + "\t| | | | | | | |/ / _ \\\n"
          + "\t| |_| | |_| |   <  __/\n"
          + "\t|____/ \\__,_|_|\\_\\___|\n";

  /**
   * run should be supplied with concrete implementations of its UI, QueryInterpreter and Storage.
   *
   * <p>For example, UI could be the Terminal UI or GUI. Storage could be in-memory or disk.
   *
   * @param ui a concrete UI implementation
   * @param q a concrete QueryInterpreter implementation
   * @param storage any Storage implementation
   */
  public static void run(UI ui, QueryInterpreter q, Storage storage) {
    Result result;

    ui.print("Hello I'm\n" + logo, "What can I do for you?");

    while (ui.hasNext()) {
      try {
        result = q.interpret(ui.nextLine());
        storage.overwrite();
        ui.print(result);
        if (result.shouldExit()) {
          break;
        }
      } catch (IOException e) {
        ui.print(String.format("Sorry, I cannot save the task to file: %s", e));
      } catch (DukeException e) {
        ui.print("Sorry, I don't understand that yet!");
      }
    }
  }
}
