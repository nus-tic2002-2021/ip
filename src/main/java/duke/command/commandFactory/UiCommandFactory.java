package duke.command.commandFactory;

import duke.TaskManager;
import duke.command.Command;

import duke.command.errorCommand.CommandInvalidRequestParameters;
import duke.command.errorCommand.CommandTaskNotFound;
import duke.command.systemCommand.CommandExitLoop;
import duke.command.taskCommand.taskAdd.CommandAddNewToDo;
import duke.command.taskCommand.taskUpdate.CommandMarkTaskAsDone;

import static duke.dukeUtility.definition.CommandPromptsAndOptions.*;


public abstract class UiCommandFactory extends CommandFactory {
    protected Command executeCommandExitLoop() {
        return new CommandExitLoop(PROMPT_EXIT_LOOP);
    }
    public abstract Command executeTextCommand(String text, TaskManager taskManager);

    protected Command executeCommandAddToDo(String text, TaskManager taskManager) {
        int minDescLength = 0;
        String taskDescription = text.replaceFirst(PROMPT_ADD_TODO, "");
        if (taskDescription.length() <= minDescLength) {
            return new CommandInvalidRequestParameters("ToDo description is too short");
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
                return new CommandTaskNotFound(argList[0]);
            }
        } catch (Exception e) {
            return new CommandInvalidRequestParameters(e.toString());
        }
        return new CommandMarkTaskAsDone(taskManager, taskId);

    }
}