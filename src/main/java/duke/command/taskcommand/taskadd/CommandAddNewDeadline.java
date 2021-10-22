package duke.command.taskcommand.taskadd;

import java.time.LocalDateTime;
import java.util.List;

import duke.TaskManager;
import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

public class CommandAddNewDeadline extends Command {
    /**
     * Task manager will add a new deadline to its collection with provided parameters
     * @param tm task manager
     * @param desc of deadline
     * @param deadline of deadline
     */
    public CommandAddNewDeadline(TaskManager tm, String desc, LocalDateTime dl) {
        super(ResponseType.TASK_CREATE_DEADLINE,
            List.of("create", desc, tm.addNewDeadline(desc, dl).getTaskDescription()));
    }
}
