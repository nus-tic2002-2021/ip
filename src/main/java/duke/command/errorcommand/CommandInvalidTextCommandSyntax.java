package duke.command.errorcommand;

import java.util.List;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandInvalidTextCommandSyntax extends Command {
    public CommandInvalidTextCommandSyntax(String message) {
        super(ResponseType.ERROR_REQUEST_INVALID_SYNTAX, List.of("Invalid syntax", message));
    }

    public String getResponse() {
        return this.getArgs().get(1) + System.lineSeparator();
    }
}
