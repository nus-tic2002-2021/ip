package duke.command.taskcommand.taskupdate;

import java.util.List;
import duke.TaskManager;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandMarkTaskAsDone extends Command {
    /**
     * get task by id in task manager and set task as done
     *
     * @param taskMgr task manager
     * @param taskId  id of task
     */
    public CommandMarkTaskAsDone(TaskManager taskMgr, Integer taskId) {
        super(ResponseType.TASK_UPDATE_COMPLETE,
            List.of("update", "done", "#" + taskMgr.getTaskByIdAndSetCompleted(taskId).getTaskId().toString()));
    }

    public String getResponse() {
        return String.join(" ", this.getArgs());
    }
}
