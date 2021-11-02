package duke;
import command.*;
import error.*;

public class Parser {
    /**
     *
     * Parser to read user input
     *
     * @param input users input message
     * @throws UnrecognizedException if action is not a recognized command
     * @throws DukeException if format has a "|" which will conflict with save format
     * @return type of command
     */
    public Command parse(String input) throws UnrecognizedException, DukeException {
        if (input.contains("|")){
            throw new DukeException("INPUT_FORMAT_ERROR");
        }
        String action;
        String[] inputArray;
        inputArray = input.split(" ", 2);
        action = inputArray[0].toLowerCase();
        switch(action){
            case "bye":
                return new ExitCommand();
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(action, inputArray[1]);
            case "delete":
                return new DeleteCommand(action, inputArray[1]);
            case "done":
                return new ModifyCommand(action, inputArray[1]);
            case "list":
                return new ListCommand();
            case "find":
                return new SearchCommand(inputArray[1]);
            case "view":
                return new ViewCommand(inputArray[1]);
            default: throw new UnrecognizedException();
        }
    }
}
