package duke.coronet.command.systemCommand;

import duke.coronet.command.Command;
import duke.coronet.dukeUtility.enums.ResponseType;

import java.util.List;

public class CommandEcho extends Command {
    public CommandEcho(String text) {
        super(ResponseType.ECHO, List.of("echo", text));
    }
}