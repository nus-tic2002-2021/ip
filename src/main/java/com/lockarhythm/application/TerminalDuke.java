package com.lockarhythm.application;

import com.lockarhythm.query.SimpleQueryInterpreter;
import com.lockarhythm.storage.Storage;
import com.lockarhythm.tasks.Task;
import com.lockarhythm.tasks.TaskList;
import com.lockarhythm.ui.TerminalUI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * TerminalDuke is the entrypoint of the Terminal UI application of Duke.
 */
final class TerminalDuke extends Application {
  public static void main(String[] args) {
    Path path = Paths.get(".", "tasks.json");
    Storage storage = new Storage<Task>(path.toString());
    ArrayList<Task> list = storage.load(Task.class);
    storage.registerList(list);
    run(new TerminalUI(), new SimpleQueryInterpreter(new TaskList(list)), storage);
  }
}
