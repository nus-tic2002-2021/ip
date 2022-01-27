package dukeMain;

import dukeMain.commands.Command;
import dukeMain.exceptions.*;
import dukeMain.tasks.*;

/** The main class of Duke Task Management Application.
 *
 * Where the programmes start to run.
 * */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /** Constructor of Duke
     *  initialize Storage and Tasklist
     *  with the default filepath
     *
     * @param filePath String
     * */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (InvalidStorageFilePathException e){
            ui.showInvalidStorageError();
        }
    }

    /** Executed after Duke is initialized.
     *  Starts to run the whole program.
     * */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
