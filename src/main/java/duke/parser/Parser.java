package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.ui.Printer;
import duke.tasklist.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Parser {

    //protected static ArrayList<Task> taskList = new ArrayList<>();

    public static Command parse(String fullCommand) throws DukeException {

        String[] taskFullDesc = fullCommand.split(" ", 2);
        String taskType = taskFullDesc[0].toUpperCase();
        EnumCommand enumCommand = null;

        try {
            enumCommand = EnumCommand.valueOf(taskType);
        } catch(IllegalArgumentException e) {
            //Use ui to display error later
            return new InvalidCommand("Command <" + taskType.toLowerCase() + "> not found :(");
        }

        try {
            switch (enumCommand) {
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
                    //return new InvalidCommand("Error executing command");
                    throw new DukeException();
            }
        }

        catch (DukeException e) {
            throw new DukeException();
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
            //Printer.printInvalidTodo();
            return new InvalidCommand("Task cannot be added. \nDescription is missing.");
        }
    }

    private static Command parseDeadline(String[] taskFullDesc) {
        try {
            String taskDesc = taskFullDesc[1];
            String[] taskDescBy = taskDesc.split(" /by ");
            Task task = new Deadline(taskDescBy[0], taskDescBy[1]);
            return new AddCommand(task);
        } catch (IndexOutOfBoundsException e) {
            //Printer.printInvalidTodo();
            return new InvalidCommand("Task cannot be added. \nDeadline or description is missing.");
        }
    }

    private static Command parseEvent(String[] taskFullDesc) {
        try {
            String taskDesc = taskFullDesc[1];
            String[] taskDescAt = taskDesc.split(" /at ");
            Task task = new Event(taskDescAt[0], taskDescAt[1]);
            return new AddCommand(task);
        } catch (IndexOutOfBoundsException e) {
            //Printer.printInvalidTodo();
            return new InvalidCommand("Task cannot be added. \nEvent time or description is missing");
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
