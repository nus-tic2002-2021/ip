package duke;

import duke.action.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

public class RunDuke {

    private static Parser parser = new Parser();

    public static void run(TaskList myList, Ui ui) {

        boolean isDukeRunning = true;

        while (isDukeRunning) {
            String userInput = ui.requestUserInput();
            String command = parser.processUserCommand(userInput);
            processCommand(command);
        }

    }

    private void processCommand(String command) {
        switch (command) {
        case "list":
            showFullList(myList);
            break;
        case "set":
            toSetPriorityTask(myList, line);
            break;
            case ""
        case "todo":
            run();
            break;
        case "save":
            run();
            break;
        case "load":
            run();
            break;
        case "find":
            run();
            break;
        case "event":
            run();
            break;
        case "delete":
            run();
            break;
        case "deadline":
            run();
            break;
        default
            run();
            break;
        }
    }


}
