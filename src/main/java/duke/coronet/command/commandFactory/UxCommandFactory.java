package duke.coronet.command.commandFactory;

import duke.coronet.command.Command;

import duke.coronet.command.errorCommand.CommandInvalidRequest;
import duke.coronet.command.systemCommand.CommandEcho;
import duke.coronet.command.systemCommand.CommandExit;
import duke.coronet.command.taskCommand.taskAdd.CommandAddNewToDo;
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

}