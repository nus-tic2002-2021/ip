package duke.coronet.command.taskCommand.taskUpdate;

import duke.coronet.command.Command;
import duke.coronet.dukeUtility.enums.ResponseType;
import duke.coronet.manager.TaskManager;

import java.util.List;

public class CommandMarkTaskAsDone extends Command {
    public CommandMarkTaskAsDone(TaskManager taskMgr, Integer taskId) {
        super(ResponseType.TASK_UPDATE_DONE_STATUS, List.of("update", "done", "#" + taskMgr.getTaskByIdAndSetDone(taskId, true).getTaskId().toString()));
    }
}
