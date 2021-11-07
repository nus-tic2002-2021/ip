import command.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.*;

/**
 * The duke class is the main class of the program
 *  duke object
 */
public class Duke {

    private TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    public Duke() {
        String filePath = "src/data/tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            ui.exceptionMessage(e);
        } catch (FileNotFoundException e) {
            ui.exceptionMessage(e);
            taskList = new TaskList();
        }
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            ui.exceptionMessage(e);
        } catch (FileNotFoundException e) {
            ui.exceptionMessage(e);
            taskList = new TaskList();
        }
    }

    public void run() {
        Ui.Welcome();
        boolean isExit = false;
        while(!isExit){
            try {
                String userInput = ui.userInput();
                ui.Separator();
                Command c = Parser.parse(userInput, taskList);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e){
                ui.errorMessage(e);
            } finally {
                ui.Separator();
            }
        }
    }

    public String dukeReply(String input) {
        try {
            Command c = Parser.parse(input, taskList);
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            c.execute(taskList, ui, storage);
            return outContent.toString();
        } catch (DukeException | IOException e) {
            return e.getMessage();
        } catch (NullPointerException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            return errors.toString();
        }
    }

    public static void main(String[] args){
        new Duke("src/data/tasks.txt").run();
    }
}
