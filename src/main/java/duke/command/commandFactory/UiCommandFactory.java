package duke.command.commandFactory;

import duke.TaskManager;
import duke.command.Command;

import duke.command.errorCommand.CommandInvalidRequestParameters;
import duke.command.systemCommand.CommandEcho;
import duke.command.systemCommand.CommandExit;
import duke.command.taskCommand.taskAdd.CommandAddNewToDo;

import static duke.dukeUtility.definition.CommandPromptsAndOptions.*;

public abstract class UiCommandFactory extends CommandFactory {
    protected Command executeCommandExitLoop() {
        return new CommandExit(PROMPT_EXIT);
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
}