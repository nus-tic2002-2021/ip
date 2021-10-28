package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;

/**
 * A <code>Duke</code> object, who is your personal assistant to keep track of all your tasks in a task list.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs Duke with this file path.
     *
     * @param filePath The file location to store and access the task list.
     */
    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.getTaskList());
        } catch (FileNotFoundException e) {
            ui.showDividerLine();
            ui.showFileNotFound();
            taskList = new TaskList();
        }
    }

    /**
     * Runs Duke interactively.
     */
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

    /**
     * Constructs and runs Duke.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("src/main/java/duke/data/tasklist.csv").run();
    }

}
