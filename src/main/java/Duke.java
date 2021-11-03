import command.Command;
import exception.DukeException;
import filewriter.Storage;
import parser.Parser;
import task.TaskList;
import ui.Ui;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke with no params.
     * Required to for javaFx application to function.
     */
    public Duke() {
        String filePath = "data/tasks.txt";
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Shows the UI and stores/loads the file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            if (c.isExit()) {
                c.execute(tasks, ui, storage);
                ui.showGoodbye();
            } else {
                c.execute(tasks, ui, storage);

            }
            return outContent.toString();
        } catch (DukeException e) {
            return e.getMessage();
        } catch (NullPointerException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            return errors.toString();
        }
    }
}

