package duke.command;

import duke.app.Parser;

/**
 * Initialise a command object based on specified user action, which includes list, done, delete, add, bye, date.
 */
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
            case DATE:
                return new DateCommand();
            default:
                return null;
        }
    }
}
