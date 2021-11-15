package duke.command;

import duke.app.Parser;

public class CommandFactory {

    public static AbstractCommand create(Parser.validActions validAction) {
        switch (validAction) {
            case LIST:
                return new ListCommand();
            case DONE:
                return new DoneCommand();
            case DELETE:
                return new DeleteCommand();
            case ADD:
                return new AddCommand();
            case BYE:
                return new ExitCommand();
            default:
                return null;
        }
    }
}
