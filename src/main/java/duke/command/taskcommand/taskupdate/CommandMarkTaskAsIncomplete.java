package duke.command.taskcommand.taskupdate;

import java.util.List;
import duke.TaskManager;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandMarkTaskAsIncomplete extends Command {
    /**
     * get task by id in task manager and set task as done
     *
     * @param taskMgr task manager
     * @param taskId  id of task
     */
    public CommandMarkTaskAsIncomplete(TaskManager taskMgr, Integer taskId) {
        super(ResponseType.TASK_UPDATE_INCOMPLETE,
            List.of("update", "not done", "#" + taskMgr.getTaskByIdAndSetIncomplete(taskId).getTaskId().toString()));
    }

    public String getResponse() {
        return String.join(" ", this.getArgs()) + System.lineSeparator();
    }
}
