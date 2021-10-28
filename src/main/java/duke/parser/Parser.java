package duke.parser;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandEnum;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.TagCommand;
import duke.tasklist.Task;
import duke.tasklist.Todo;
import duke.tasklist.Event;
import duke.tasklist.Deadline;

/**
 *  A <code>Parser</code> object to parse user commands.
 */
public class Parser {

    /**
     * Returns a Command object from a user command String object.
     *
     * @param fullCommand Full user command string.
     */
    public static Command parse(String fullCommand) {

        String[] taskFullDesc = fullCommand.split(" ", 2);
        String taskType = taskFullDesc[0].toUpperCase();
        CommandEnum userCommand = null;

        try {
            userCommand = CommandEnum.valueOf(taskType);
        } catch (IllegalArgumentException e) {
            return new InvalidCommand("Oops! No such command found.\n" +
                                      "Only list, todo, deadline, event, done, delete, find, tag and bye ☺");
        }

        switch (userCommand) {
        case LIST:
            return parseList();
        case TODO:
            return parseTodo(taskFullDesc);
        case DEADLINE:
            return parseDeadline(taskFullDesc);
        case EVENT:
            return parseEvent(taskFullDesc);
        case DONE:
            return parseDone(taskFullDesc);
        case DELETE:
            return parseDelete(taskFullDesc);
        case FIND:
            return parseFind(taskFullDesc);
        case TAG:
            return parseTag(taskFullDesc);
        case BYE:
            return parseBye();
        default:
            return new InvalidCommand("There is an error while executing your command ☹");
        }

    }

    private static Command parseList() {
        return new ListCommand();
    }

    private static Command parseTodo(String[] taskFullDesc) {
        try {
            String taskDesc = taskFullDesc[1];
            Task task = new Todo(taskDesc);
            return new AddCommand(task);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Oops! Task cannot be added.\nPlease provide its description ☺");
        }
    }

    private static Command parseDeadline(String[] taskFullDesc) {
        try {
            String taskDesc = taskFullDesc[1];
            String[] taskDescBy = taskDesc.split(" /by ");
            Task task = new Deadline(taskDescBy[0], taskDescBy[1]);
            return new AddCommand(task);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Oops! Task cannot be added. Please check your syntax and\nprovide the correct" +
                    " description and deadline ☺");
        } catch (Exception e) {
            return new InvalidCommand("Oops! Task cannot be added.\n"+e.getMessage()+".");
        }
    }

    private static Command parseEvent(String[] taskFullDesc) {
        try {
            String taskDesc = taskFullDesc[1];
            String[] taskDescAt = taskDesc.split(" /at ");
            Task task = new Event(taskDescAt[0], taskDescAt[1]);
            return new AddCommand(task);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Oops! Task cannot be added. Please check your syntax and\nprovide the correct" +
                    " description and datetime ☺");
        } catch (Exception e) {
            return new InvalidCommand("Oops! Task cannot be added.\n"+e.getMessage()+".");
        }
    }

    private static Command parseDone(String[] taskFullDesc) {
        try {
            String taskDesc = taskFullDesc[1];
            int taskId = Integer.parseInt(taskDesc);
            return new DoneCommand(taskId);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Task id is missing.");
        }
    }

    private static Command parseDelete(String[] taskFullDesc) {
        try {
            String taskDesc = taskFullDesc[1];
            int taskId = Integer.parseInt(taskDesc);
            return new DeleteCommand(taskId);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Task id is missing.");
        }
    }

    private static Command parseFind(String[] taskFullDesc) {
        try {
            String taskDesc = taskFullDesc[1];
            return new FindCommand(taskDesc);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Task description is missing.");
        }
    }

    private static Command parseTag(String[] taskFullDesc) {
        try {
            String taskDesc = taskFullDesc[1];
            String[] taskTag = taskDesc.split(" ", 2);
            int taskId = Integer.parseInt(taskTag[0]);
            String tagDesc = taskTag[1];
            return new TagCommand(taskId, tagDesc);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Task id or tag description is missing.");
        }
    }

    private static Command parseBye() {
        return new ByeCommand();
    }

}
