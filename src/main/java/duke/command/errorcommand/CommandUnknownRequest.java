package duke.command.errorcommand;

import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

import java.util.List;

public class CommandUnknownRequest extends Command {
    public CommandUnknownRequest(String text) {
        super(ResponseType.ERROR_REQUEST_UNKNOWN, List.of("?", text));
    }
}
