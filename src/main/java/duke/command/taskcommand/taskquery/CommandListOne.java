package duke.command.taskcommand.taskquery;

import static duke.dukeutility.prettify.Prettify.prettifyTasks;
import java.util.List;
import duke.TaskManager;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandListOne extends Command {
    public CommandListOne(TaskManager taskMgr, Integer taskId) {
        super(ResponseType.TASK_LIST_FIND,
            List.of("list", prettifyTasks(taskMgr.getTasksWithId(taskId)), taskId.toString()));
    }

    public String getResponse() {
        return "See: " + this.getArgs().get(2) + System.lineSeparator() + this.getArgs().get(1);
    }
}
