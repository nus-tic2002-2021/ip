package duke.coronet.command.commandFactory;

import duke.coronet.command.Command;

import duke.coronet.command.systemCommand.CommandEcho;
import duke.coronet.command.systemCommand.CommandExit;
import duke.coronet.manager.FileResourceManager;
import duke.coronet.manager.TaskManager;

import static duke.coronet.dukeUtility.definition.CommandPromptsAndOptions.*;

public abstract class UxCommandFactory extends CommandFactory {
    protected Command executeCommandEcho(String text) {
        return new CommandEcho(text);
    }
    protected Command executeCommandExitLoop() {
        return new CommandExit(PROMPT_EXIT);
    }
    public abstract Command executeTextCommand(String text, TaskManager taskManager, FileResourceManager frm);
}