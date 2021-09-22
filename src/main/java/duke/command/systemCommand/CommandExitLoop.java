package duke.command.systemCommand;

import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

import java.util.List;

public class CommandExitLoop extends Command {
    public CommandExitLoop(String invocation) {
        super(ResponseType.EXIT_LOOP, List.of(invocation));
    }
}