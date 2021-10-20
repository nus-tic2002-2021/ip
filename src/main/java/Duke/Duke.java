package Duke;

import Duke.exception.DukeException;
import Duke.parser.Command;
import Duke.parser.Parser;
import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.UI;


public class Duke {
    private static Storage storage;
    private UI ui;
    private TaskList taskList;

    /**
     * Beginning function taking in filepath
     *
     * @param filePath - filepath to storage
     */
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

    public void run() throws DukeException {
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

    public static void main(String[] args) throws DukeException {
        new Duke("duke.txt").run();
    }
}
