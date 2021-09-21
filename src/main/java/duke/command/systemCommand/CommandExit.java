package duke.command.systemCommand;

import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

import java.util.List;

public class CommandExit extends Command {
    public CommandExit(String invocation) {
        super(ResponseType.EXIT_LOOP, List.of(invocation));
    }
}