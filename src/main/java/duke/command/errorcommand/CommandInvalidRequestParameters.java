package duke.command.errorcommand;

import java.util.List;

import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

public class CommandInvalidRequestParameters extends Command {
    public CommandInvalidRequestParameters(String message) {
        super(ResponseType.ERROR_REQUEST_INVALID_PARAMETERS, List.of("Invalid request parameters", message));
    }
}
