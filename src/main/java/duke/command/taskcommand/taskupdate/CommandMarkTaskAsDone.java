package duke.command.taskcommand.taskupdate;

import java.util.List;
import duke.TaskManager;
import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

public class CommandMarkTaskAsDone extends Command {
    public CommandMarkTaskAsDone(TaskManager taskMgr, Integer taskId) {
        super(ResponseType.TASK_UPDATE_DONE_STATUS,
            List.of("update", "done", "#" + taskMgr.getTaskByIdAndSetDoneStatus(taskId, true).getTaskId().toString()));
    }
}
