package duke.command.commandFactory;

import duke.command.Command;

import duke.command.systemCommand.CommandEcho;
import duke.command.systemCommand.CommandExit;

import static duke.dukeUtility.definition.CommandPromptsAndOptions.*;

public abstract class UiCommandFactory extends CommandFactory {
    protected Command executeCommandEcho(String text) {
        return new CommandEcho(text);
    }
    protected Command executeCommandExitLoop() {
        return new CommandExit(PROMPT_EXIT);
    }
    public abstract Command executeTextCommand(String text);
}