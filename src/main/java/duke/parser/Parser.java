package duke.parser;

import duke.command.*;
import duke.tasklist.*;

public class Parser {

    public static Command parse(String fullCommand) {

        String[] taskFullDesc = fullCommand.split(" ", 2);
        String taskType = taskFullDesc[0].toUpperCase();
        EnumCommand userCommand = null;

        try {
            userCommand = EnumCommand.valueOf(taskType);
        } catch (IllegalArgumentException e) {
            return new InvalidCommand("Oops! No such command found.\n" +
                                      "Only list, todo, deadline, event, done, delete and bye ☺");
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
        }
        catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Task id is missing.");
        }
    }

    private static Command parseDelete(String[] taskFullDesc) {
        try {
            String taskDesc = taskFullDesc[1];
            int taskId = Integer.parseInt(taskDesc);
            return new DeleteCommand(taskId);
        }
        catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Task id is missing.");
        }
    }

    private static Command parseBye () {
        return new ByeCommand();
    }

}
