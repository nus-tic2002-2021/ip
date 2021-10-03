package parser;

import commands.*;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.ToDoTask;

public class Parser {

    public static Command parse(String fullCommand) {

        // split the fullCommand and get the command keyword
        String[] args = fullCommand.split(" ", 2);
        String keyword = args[0].toUpperCase();

        CommandEnum commandEnum = null;
        try {
            commandEnum = CommandEnum.valueOf(keyword);
        } catch(IllegalArgumentException e) {
            //Use ui to display error later
            return new InvalidCommand("Command <" + keyword.toLowerCase() + "> not found :(");
        }

        switch(commandEnum){
            case LIST:
                return doList();
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
            case BYE:
                return doExit();
            default:
                return new InvalidCommand("Error executing command");
        }
    }

    private static Command doList(){
        return new ListCommand();
    }

    private static Command doAddTodo (String[] args){
        try {
            Task task = new ToDoTask(args[1]);
            return new AddCommand(task);
        }
        catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Task cannot be added. \nDescription is missing.");
        }
    }

    private static Command doAddDeadline(String[] args){
        try {
            String[] deadlineInfo = args[1].split(" /by ");
            Task task = new DeadlineTask(deadlineInfo[0], deadlineInfo[1]);
            return new AddCommand(task);
        }
        catch(IndexOutOfBoundsException e) {
            return new InvalidCommand("Task cannot be added. \nDeadline or description is missing.");
        }
    }

    private static Command doAddEvent(String[] args){
        try {
            String[] eventInfo = args[1].split(" /at ");
            Task task = new EventTask(eventInfo[0], eventInfo[1]);
            return new AddCommand(task);
        }
        catch(IndexOutOfBoundsException e) {
            return new InvalidCommand("Task cannot be added. \nEvent time or description is missing");
        }
    }

    private static Command doComplete(String[] args){
        try {
            int taskId = Integer.parseInt(args[1]);
            return new CompleteCommand(taskId);
        }
        catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Task id is missing.");
        }
    }

    private static Command doDelete(String[] args){
        try {
            int taskId = Integer.parseInt(args[1]);
            return new DeleteCommand(taskId);
        }
        catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("Task id is missing.");
        }
    }

    private static Command doExit(){
        return new ExitCommand();
    }
}
