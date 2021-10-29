import commands.Command;
import parser.Parser;
import storage.Storage;
import ui.Ui;
import tasks.*;
import exceptions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main class of the Duke program.
 * The Duke program is a ChatBot that helps user to keep track of to-dos, deadlines and events.
 */
public class Duke {

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Constructor of <code>Duke</code>.
     *
     * @param filePath Location of the file which stores the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            try {
                storage.init();
            } catch (IOException error) {
                ui.showError(error.getMessage());
            }
            taskList = new TaskList();
        }
    }

    /** Method to start running the Duke program. */
    public void runCmd() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String reply(String input) {
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            return c.execute(taskList, ui, storage);
        } catch (DukeException | IOException e) {
            // ui.showError(e.getMessage());
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").runCmd();
    }
}
