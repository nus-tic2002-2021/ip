package Duke;

import Duke.exception.DukeException;
import Duke.parser.Command;
import Duke.parser.Parser;
import Duke.storage.Storage;
import Duke.task.*;
import Duke.ui.UI;

import java.io.IOException;


public class Duke {
    private static Storage storage;
    private UI ui;
    private TaskList taskList;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
        ui.showExit();
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("duke.txt").run();
    }
}
