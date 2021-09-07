package duke.coronet.command.systemCommand;

import duke.coronet.command.Command;
import duke.coronet.dukeUtility.enums.ResponseType;

import java.util.List;

public class CommandExitLoop extends Command {
    public CommandExitLoop(String invocation) {
        super(ResponseType.EXIT_LOOP, List.of(invocation));
    }
}