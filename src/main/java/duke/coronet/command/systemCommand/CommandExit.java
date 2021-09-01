package duke.coronet.command.systemCommand;

import duke.coronet.command.Command;
import duke.coronet.dukeUtility.enums.ResponseType;

import java.util.List;

public class CommandExit extends Command {
    public CommandExit(String invocation) {
        super(ResponseType.EXIT_LOOP, List.of(invocation));
    }
}