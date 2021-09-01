package duke.coronet.command.errorCommand;

import duke.coronet.command.Command;
import duke.coronet.dukeUtility.enums.ResponseType;

import java.util.List;

public class CommandExecutionError extends Command {
    public CommandExecutionError(Exception e, String when) {
        super(ResponseType.ERROR_COMMAND_EXECUTION, List.of("Error during execution of command ", when, e.toString()));
    }
}