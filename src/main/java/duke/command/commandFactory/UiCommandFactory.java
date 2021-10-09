package duke.command.commandFactory;

import duke.FileResourceManager;
import duke.TaskManager;
import duke.command.Command;

import duke.command.errorCommand.CommandExecutionError;
import duke.command.errorCommand.CommandInvalidRequestParameters;
import duke.command.errorCommand.CommandInvalidTextCommandSyntax;
import duke.command.errorCommand.CommandTaskNotFound;
import duke.command.systemCommand.CommandExitLoop;
import duke.command.taskCommand.taskAdd.CommandAddNewDeadline;
import duke.command.taskCommand.taskAdd.CommandAddNewEvent;
import duke.command.taskCommand.taskAdd.CommandAddNewToDo;
import duke.command.taskCommand.taskUpdate.CommandDeleteTask;
import duke.command.taskCommand.taskUpdate.CommandMarkTaskAsDone;
import duke.dukeExceptions.DukeInvalidSyntaxException;

import static duke.dukeUtility.definition.CommandPromptsAndOptions.*;


public abstract class UiCommandFactory extends CommandFactory {
    protected Command executeCommandExitLoop() {
        return new CommandExitLoop(PROMPT_EXIT_LOOP);
    }
    public abstract Command executeTextCommand(String text, TaskManager taskManager, FileResourceManager frm);

    protected Command executeCommandAddToDo(String text, TaskManager taskManager) {
        int minDescLength = 0;
        String taskDescription = text.replaceFirst(PROMPT_ADD_TODO, "");
        if (taskDescription.length() <= minDescLength) {
            return new CommandInvalidRequestParameters("ToDo description is too short");
        }
        return new CommandAddNewToDo(taskManager, taskDescription);
    }

    protected Command executeCommandAddDeadline(String text, TaskManager taskManager) {

        String argLine;
        String[] argList;
        String taskDescription;
        String deadline;
        try {
            argLine = text.replaceFirst(PROMPT_ADD_DEADLINE, "");
            String addDeadlineStringDelimiter = ADD_DEADLINE_DEADLINE_DELIMITER;
            argList = argLine.split(addDeadlineStringDelimiter);
            int expectedArgsLength = 2;
            if (argList.length != expectedArgsLength) {
                throw new DukeInvalidSyntaxException("Expected " + expectedArgsLength + " arguments delimited by \""+ addDeadlineStringDelimiter + "\"");
            }
            taskDescription = argList[0];
            deadline = argList[1];
        }catch(DukeInvalidSyntaxException e){
            return new CommandInvalidTextCommandSyntax(e.getMessage());
        } catch (Exception e){
            return new CommandExecutionError(e,"Unknown Error");
        }
        return new CommandAddNewDeadline(taskManager, taskDescription, deadline);
    }

    protected Command executeCommandAddEvent(String text, TaskManager taskManager) {
        String argLine;
        String[] argList;
        String[] scheduleOptionList;
        String taskDescription;
        String from;
        String to;
        try {
            argLine = text.replaceFirst(PROMPT_ADD_EVENT, "");
            argList = argLine.split(ADD_EVENT_SCHEDULE_DELIMITER);
            if (argList.length != 2) {
                throw new Exception("Request line for adding event does not conform to syntax.");
            }
            taskDescription = argList[0];
            scheduleOptionList = argList[1].split("-");
            if (scheduleOptionList.length != 2) {
                throw new Exception("Request line for adding event does not conform to syntax.");
            }
            from = scheduleOptionList[0];
            to = scheduleOptionList[1];
        } catch (Exception e) {
            return new CommandInvalidRequestParameters(e.toString());
        }
        return new CommandAddNewEvent(taskManager, taskDescription, from, to);
    }


    protected Command executeCommandMarkTaskAsDone(String text, TaskManager taskManager) {
        String argLine;
        String[] argList;
        Integer taskId;
        try {
            argLine = text.replaceFirst(PROMPT_UPDATE_DONE, "");
            argList = argLine.split(" ");
            if (argList.length != 1) {
                throw new Exception("Invalid syntax.");
            }
            taskId = Integer.parseInt(argList[0]);
            if (!taskManager.containsTaskId(taskId)) {
                return new CommandTaskNotFound(argList[0]);
            }
        } catch (Exception e) {
            return new CommandInvalidRequestParameters(e.toString());
        }
        return new CommandMarkTaskAsDone(taskManager, taskId);
    }

    protected Command executeCommandDeleteTask(String text, TaskManager taskManager) {
        String argLine;
        String[] argList;
        Integer taskId;
        try {
            argLine = text.replaceFirst(PROMPT_DELETE_TASK, "");
            argList = argLine.split(" ");
            if (argList.length != 1) {
                throw new Exception("Invalid syntax.");
            }
            taskId = Integer.parseInt(argList[0]);
            if (!taskManager.containsTaskId(taskId)) {
                return new CommandTaskNotFound(argList[0]);
            }
        } catch (Exception e) {
            return new CommandInvalidRequestParameters(e.toString());
        }
        return new CommandDeleteTask(taskManager, taskId);
    }
}