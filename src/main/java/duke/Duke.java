package duke;

import duke.command.*;
import duke.parser.*;
import duke.storage.*;
import duke.tasklist.*;
import duke.ui.*;

import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.getTaskList());
        }
        catch (FileNotFoundException e) {
            ui.showDividerLine();
            ui.showFileNotFound();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDividerLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showException(e.getMessage());
            } finally {
                ui.showDividerLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/java/duke/data/tasklist.csv").run();
    }

}
