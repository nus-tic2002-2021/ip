package duke.command.systemcommand;

import java.util.List;

import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandExitLoop extends Command {
    public CommandExitLoop(String invocation) {
        super(ResponseType.EXIT_LOOP, List.of(invocation));
    }
}
