package duke.coronet.command.commandFactory;

import duke.coronet.command.Command;

import duke.coronet.command.errorCommand.CommandInvalidRequest;
import duke.coronet.command.errorCommand.CommandTaskNotFound;
import duke.coronet.command.systemCommand.CommandEcho;
import duke.coronet.command.systemCommand.CommandExit;
import duke.coronet.command.taskCommand.taskAdd.CommandAddNewToDo;
import duke.coronet.command.taskCommand.taskUpdate.CommandMarkTaskAsDone;
import duke.coronet.manager.FileResourceManager;
import duke.coronet.manager.TaskManager;

import static duke.coronet.dukeUtility.definition.CommandPromptsAndOptions.*;
import static duke.coronet.dukeUtility.definition.CommandPromptsAndOptions.PROMPT_ADD_TODO;

public abstract class UxCommandFactory extends CommandFactory {
    protected Command executeCommandEcho(String text) {
        return new CommandEcho(text);
    }
    protected Command executeCommandExitLoop() {
        return new CommandExit(PROMPT_EXIT);
    }
    public abstract Command executeTextCommand(String text, TaskManager taskManager, FileResourceManager frm);

    protected Command executeCommandAddToDo(String text, TaskManager taskManager) {
        int minDescLength = 0;
        String taskDescription = text.replaceFirst(PROMPT_ADD_TODO, "");
        if (taskDescription.length() <= minDescLength) {
            return new CommandInvalidRequest("ToDo description is too short");
        }
        return new CommandAddNewToDo(taskManager, taskDescription);
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
                return new CommandTaskNotFound(text);
            }
        } catch (Exception e) {
            return new CommandInvalidRequest(e.toString());
        }
        return new CommandMarkTaskAsDone(taskManager, taskId);

    }
}