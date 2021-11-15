import duke.command.Command;
import duke.DukeException;
import duke.Storage;
import duke.command.CommandResult;
import duke.command.Exit;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.Ui.UserInterface;


public class Duke {
    UserInterface ui;
    TaskList taskList;
    Storage storage;

    public Duke() {
        this.ui = new UserInterface();
        this.taskList = new TaskList();
        this.storage = new Storage();
    }

    public void run() {
        ui.logo();
        ui.showWelcomeMessage();
        this.userCommand();
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    public void userCommand() {
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.getInput();
            ui.showLine(); // show the divider line ("_______")
            Command c = (new Parser()).parseCommand(fullCommand);
            CommandResult result = execute(c);
            ui.getResponse(result);
            isExit = Exit.isExitCommand(c);
        }
            ui.showLine();
    }


    public CommandResult execute (Command command) {
        command.inputData(taskList);
        storage.save(taskList);
        return command.execute();
    }


    public String getResponse(String userInput) {
        Command command = (new Parser()).parseCommand(userInput);
        CommandResult result = execute(command);
        return ui.getResponse(result);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();

    }

}
