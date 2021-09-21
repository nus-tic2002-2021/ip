package duke.command.systemCommand;

import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

import java.util.List;

public class CommandEcho extends Command {
    public CommandEcho(String text) {
        super(ResponseType.ECHO, List.of("echo", text));
    }
}