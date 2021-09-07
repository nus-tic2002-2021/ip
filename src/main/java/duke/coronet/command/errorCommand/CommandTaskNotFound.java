package duke.coronet.command.errorCommand;

import duke.coronet.command.Command;
import duke.coronet.dukeUtility.enums.ResponseType;

import java.util.List;

public class CommandTaskNotFound extends Command {
    public CommandTaskNotFound(String text) {
        super(ResponseType.TASK_NOT_FOUND, List.of("Task Not Found ", text));
    }
}

