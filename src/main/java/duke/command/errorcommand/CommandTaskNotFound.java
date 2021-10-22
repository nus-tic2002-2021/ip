package duke.command.errorcommand;

import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

import java.util.List;

public class CommandTaskNotFound extends Command {
    public CommandTaskNotFound(String text) {
        super(ResponseType.TASK_NOT_FOUND, List.of("Task Not Found ", text));
    }
}

