package duke;

import duke.action.Parser;
import duke.command.Command;
import duke.storage.FileAccess;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

public class RunDuke {

    private static TaskList myList;
    private static Ui ui;
    private static FileAccess fileAccess;
    private static Parser parser;
    private static Command cmd;
    private static Scanner in;


    public RunDuke(TaskList myList, Ui ui, FileAccess fileAccess) {
        this.myList = myList;
        this.ui = ui;
        this.fileAccess = fileAccess;
        parser = new Parser();
        cmd = new Command(parser);
        in = new Scanner(System.in);
    }

    public void run() {

        boolean isDukeRunning = true;

        while (isDukeRunning) {
            String userInput = ui.requestUserInput(in);
            String userCommand = parser.processUserCommand(userInput);
            isDukeRunning = canProcessCommand(userCommand, userInput);
        }
    }

    private static boolean canProcessCommand(String userCommand, String userInput) {
        switch (userCommand) {
        case "bye":
            return  false;
        case "list":
            cmd.showFullList(myList);
            return true;
        case "set":
            cmd.setPriorityTask(myList);
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
        case "load":
            // loadTask(myList); todo
            return true;
        case "find":
            cmd.findTask(myList, userInput);
            return true;
        case "event":
            cmd.addTaskEvent(myList, userInput);
            return true;
        case "delete":
            cmd.deleteTask(myList, userInput);
            return true;
        case "deadline":
            cmd.addTaskDeadline(myList, userInput);
            return true;
        default:
            cmd.showInvalidCommand(); // todo
            return true;
        }
    }


}
