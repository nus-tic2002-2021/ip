package duke.coronet.command.errorCommand;

import duke.coronet.command.Command;
import duke.coronet.dukeUtility.enums.ResponseType;

import java.util.List;

public class CommandUnknownRequest extends Command {
    public CommandUnknownRequest(String text) {
        super(ResponseType.ERROR_REQUEST_UNKNOWN, List.of("?", text));
    }
}
