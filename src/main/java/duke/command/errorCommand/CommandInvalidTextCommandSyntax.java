package duke.command.errorCommand;

import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

import java.util.List;

public class CommandInvalidTextCommandSyntax extends Command {
    public CommandInvalidTextCommandSyntax(String message) {
        super(ResponseType.ERROR_REQUEST_INVALID_SYNTAX, List.of("Invalid syntax", message));
    }
}
