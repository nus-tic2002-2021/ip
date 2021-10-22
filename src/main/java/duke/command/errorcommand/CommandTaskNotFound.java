package duke.command.errorcommand;

import java.util.List;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandTaskNotFound extends Command {
    public CommandTaskNotFound(String text) {
        super(ResponseType.TASK_NOT_FOUND, List.of("Task Not Found ", text));
    }
}

