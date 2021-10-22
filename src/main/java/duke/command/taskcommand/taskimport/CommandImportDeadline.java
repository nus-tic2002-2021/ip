package duke.command.taskcommand.taskimport;

import java.util.List;
import duke.TaskManager;
import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;
import duke.task.model.Deadline;


public class CommandImportDeadline extends Command {
    /**
     * Import a deadline to a task manager
     *
     * @param deadline deadline
     * @param taskMgr  task manager
     */
    public CommandImportDeadline(Deadline deadline, TaskManager taskMgr) {
        super(ResponseType.TASK_IMPORT_DEADLINE,
            List.of("create", deadline.getTaskDescription(), taskMgr.importDeadline(deadline).getTaskDescription()));
    }
}
