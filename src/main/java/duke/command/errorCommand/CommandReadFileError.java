package duke.command.errorCommand;

import duke.command.CommandJsonResponse;
import duke.dukeUtility.enums.ResponseType;

import java.util.List;

public class CommandReadFileError extends CommandJsonResponse {
    public CommandReadFileError(String message) {
        super(ResponseType.ERROR_INVALID_READ_FILE_PATH, List.of(message), null);
    }
}
