package duke.command.errorcommand;

import java.util.List;
import duke.command.CommandJsonResponse;
import duke.dukeutility.enums.ResponseType;

public class CommandReadFileError extends CommandJsonResponse {
    public CommandReadFileError(String message) {
        super(ResponseType.ERROR_INVALID_READ_FILE_PATH, List.of(message), null);
    }

    @Override
    public String getResponse() {
        return "Read path not found/invalid. " + System.lineSeparator();
    }
}
