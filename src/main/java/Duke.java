import duke.app.Parser;
import duke.app.TaskList;
import duke.app.Ui;
import duke.command.AbstractCommand;
import duke.exceptions.FileProcessError;
import duke.exceptions.InvalidUserInputException;
import duke.storage.Storage;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public Duke(String filePath) {
        parser = new Parser();
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadFile());
        } catch (FileProcessError e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    public void run() throws IOException {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            String inputCommand = ui.readCommand();
            ui.showLine();
            parser.setUserInput(inputCommand);
            try {
                tasks.setParsedUserInputs(parser.parseAndCheckInput());
                AbstractCommand command = parser.parseCommand();
                command.execute(tasks, storage, ui);
                isExit = command.isExit();
            } catch (InvalidUserInputException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            Duke app = new Duke("data/tasks.txt");
            app.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

