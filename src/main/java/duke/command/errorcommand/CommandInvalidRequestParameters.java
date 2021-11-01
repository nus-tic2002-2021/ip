package duke.command.errorcommand;

import java.util.List;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandInvalidRequestParameters extends Command {
    public CommandInvalidRequestParameters(String message) {
        super(ResponseType.ERROR_REQUEST_INVALID_PARAMETERS, List.of("Invalid request parameters", message));
    }


    public String getResponse() {
        return this.getArgs().get(1);
    }
}
