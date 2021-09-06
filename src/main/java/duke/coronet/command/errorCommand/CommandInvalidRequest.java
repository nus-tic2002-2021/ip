package duke.coronet.command.errorCommand;

import duke.coronet.command.Command;
import duke.coronet.dukeUtility.enums.ResponseType;

import java.util.List;

public class CommandInvalidRequest extends Command {
    public CommandInvalidRequest(String message) {
        super(ResponseType.ERROR_REQUEST_INVALID, List.of("?", message));
    }
}
