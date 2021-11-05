package parser;

import commands.*;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.ToDoTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *  A <code>Parser</code> object deals with making sense of the user command.
 */
public class Parser {
    /**
     * Returns a <code>Command</code> object converted from the input command string.
     * If the input command string is invalid, an <code>InvalidCommand</code> object will be returned.
     *
     * @param fullCommand Input command string.
     */
    public static Command parse(String fullCommand) {
        assert fullCommand != null : "Command be null";

        // split the fullCommand and get the command keyword
        String[] args = fullCommand.split(" ", 2);
        String keyword = args[0].toUpperCase();

        CommandEnum commandEnum = null;
        try {
            commandEnum = CommandEnum.valueOf(keyword);
        } catch (IllegalArgumentException e) {
            //Use ui to display error later
            return new InvalidCommand("Command <" + keyword.toLowerCase() + "> not found.");
        }

        switch(commandEnum){
        case LIST:
            return doList();
        case HELP:
            return doHelp();
        case TODO:
            return doAddTodo(args);
        case DEADLINE:
            return doAddDeadline(args);
        case EVENT:
            return doAddEvent(args);
        case DONE:
            return doComplete(args);
        case DELETE:
            return doDelete(args);
        case FIND:
            return doFind(args);
        case VIEW:
            return doView(args);
        case BYE:
            return doExit();
        default:
            return new InvalidCommand("Error executing command");
        }
    }

    private static Command doList(){
        return new ListCommand();
    }

    private static Command doHelp(){
        return new HelpCommand();
    }

    private static Command doAddTodo (String[] args){
        String description = args[1];
        boolean descriptionMissing = description.isBlank() || description.isEmpty();
        try {
            if (descriptionMissing) {
                return new InvalidCommand("Task cannot be added. " +
                        "\nDescription is missing.");
            }
            Task task = new ToDoTask(description);
            return new AddCommand(task);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Task cannot be added. " +
                    "\nDescription is missing.");
        }
    }

    private static Command doAddDeadline(String[] args){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            String[] deadlineInfo = args[1].split(" /by ");
            String description = deadlineInfo[0];
            boolean descriptionMissing = description.isBlank() || description.isEmpty();
            if (descriptionMissing) {
                return new InvalidCommand("Task cannot be added. " +
                        "\nDescription is missing.");
            }
            LocalDateTime deadline = LocalDateTime.parse(deadlineInfo[1], formatter);
            Task task = new DeadlineTask(description, deadline);
            return new AddCommand(task);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Task cannot be added. " +
                    "\nDeadline or description is missing.");
        } catch (DateTimeParseException e) {
            return new InvalidCommand("Task cannot be added. " +
                    "\nPlease enter deadline in the format of 'yyyy-MM-dd HHmm'");
        }
    }

    private static Command doAddEvent(String[] args){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            String[] eventInfo = args[1].split(" /at ");
            String description = eventInfo[0];
            boolean descriptionMissing = eventInfo[0].isBlank() || eventInfo[0].isEmpty();
            if (descriptionMissing) {
                return new InvalidCommand("Task cannot be added. " +
                        "\nDescription is missing.");
            }

            String[] times = eventInfo[1].split(" -to ");
            LocalDateTime start = LocalDateTime.parse(times[0], formatter);
            LocalDateTime end = LocalDateTime.parse(times[1], formatter);
            Task task = new EventTask(description, start, end);
            return new AddCommand(task);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Task cannot be added. \n" +
                    "Event time or description is missing.");
        } catch (DateTimeParseException e) {
            return new InvalidCommand("Task cannot be added. \n" +
                    "Please enter event datetime in the format of 'yyyy-MM-dd HHmm -to yyyy-MM-dd HHmm'");
        }
    }

    private static Command doComplete(String[] args){
        try {
            int taskId = Integer.parseInt(args[1]);
            return new CompleteCommand(taskId);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Task id is missing.");
        }
    }

    private static Command doDelete(String[] args){
        try {
            int taskId = Integer.parseInt(args[1]);
            return new DeleteCommand(taskId);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Task id is missing.");
        }
    }

    private static Command doFind(String[] args){
        try {
            String keyword = args[1];
            return new FindCommand(keyword);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Search keyword is missing.");
        }
    }

    private static Command doView(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(args[1], formatter);
            return new ViewCommand(date);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Date is missing.");
        } catch (DateTimeParseException e) {
            return new InvalidCommand("Please enter the date in the format of 'yyyy-MM-dd'");
        }
    }

    private static Command doExit(){
        return new ExitCommand();
    }
}
