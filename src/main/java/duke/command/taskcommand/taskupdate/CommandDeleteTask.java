package duke.command.taskcommand.taskupdate;

import java.util.List;
import duke.TaskManager;
import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;


public class CommandDeleteTask extends Command {
    /**
     * Delete a task by id of task manager
     *
     * @param taskMgr task manager to delete task from.
     * @param id      of task
     */
    public CommandDeleteTask(TaskManager taskMgr, Integer id) {
        super(ResponseType.TASK_DELETE_TASK,
            List.of("delete", id.toString(), taskMgr.softDeleteById(id).toString()));
    }
}
