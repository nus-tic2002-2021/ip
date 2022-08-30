package duke;

import java.util.Scanner;

import duke.action.Parser;
import duke.command.Command;
import duke.storage.FileAccess;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * Duke class that run the main Duke program
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class RunDuke {

    private static TaskList myList;
    private static Ui ui;
    private static FileAccess fileAccess;
    private static Parser parser;
    private static Command cmd;
    private static Scanner scanner;

    /**
     * Constructor
     */
    public RunDuke(TaskList myList, Ui ui, FileAccess fileAccess) {
        this.myList = myList;
        this.ui = ui;
        this.fileAccess = fileAccess;
        parser = new Parser();
        cmd = new Command(parser);
        scanner = new Scanner(System.in);
    }

    /**
     * Execute the run
     */
    public void run() {

        boolean isDukeRunning = true;

        while (isDukeRunning) {
            String userInput = ui.requestUserInput(scanner);
            if (userInput.isEmpty()) {
                Message.msgInvalidInput();
            } else {
                String userCommand = parser.processUserCommand(userInput);
                isDukeRunning = canProcessCommand(userCommand, userInput);
            }
        }
    }

    /**
     * Read user command and execute respective methods accordingly
     * <p>
     * Accepted user input: bye, list, set, done, todo, save
     * Accepted user input: find, info, event, delete, deadline
     * <p>
     * method returns true for all commands and non-accepted user input
     * method return false for bye command.
     *
     * @param userCommand String that represents the command type by user
     * @param userInput   String that represents the full user input
     * @return boolean false if the command is "bye"; true if otherwise
     */
    private static boolean canProcessCommand(String userCommand, String userInput) {

        assert !userCommand.isEmpty() : "userCommand should not be empty";
        assert !userInput.isEmpty() : "userInput should not be empty";

        switch (userCommand) {
        case "bye":
            return false;
        case "list":
            cmd.showFullList(myList);
            return true;
        case "set":
            cmd.setPriorityTask(myList, scanner);
            return true;
        case "done":
            cmd.markTaskDone(myList, userInput);
            return true;
        case "todo":
            cmd.addTaskToDo(myList, userInput);
            return true;
        case "save":
            cmd.saveTask(myList, fileAccess);
            return true;
        case "find":
            cmd.findTask(myList, userInput);
            return true;
        case "info":
            cmd.showInfo();
            return true;
        case "event":
            cmd.addTaskEvent(myList, userInput);
            return true;
        case "undone":
            cmd.markTaskUnDone(myList, userInput);
            return true;
        case "delete":
            cmd.deleteTask(myList, userInput);
            return true;
        case "deadline":
            cmd.addTaskDeadline(myList, userInput);
            return true;
        default:
            cmd.showInvalidCommand();
            return true;
        }
    }
}
