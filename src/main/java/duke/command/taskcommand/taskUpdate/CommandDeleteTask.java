package duke.command.taskcommand.taskUpdate;

import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;
import duke.TaskManager;

import java.util.List;


public class CommandDeleteTask extends Command {
    public CommandDeleteTask(TaskManager taskMgr, Integer taskId) {
        super(ResponseType.TASK_DELETE_TASK, List.of("delete", taskId.toString(),taskMgr.softDeleteById(taskId).toString()));
    }
}