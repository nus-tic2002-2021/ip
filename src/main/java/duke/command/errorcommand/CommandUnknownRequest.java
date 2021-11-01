package duke.command.errorcommand;

import java.util.List;

import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandUnknownRequest extends Command {
    public CommandUnknownRequest(String text) {
        super(ResponseType.ERROR_REQUEST_UNKNOWN, List.of("?", text));
    }

    public String getResponse() {
        return "Unknown command. . .";
    }
}
