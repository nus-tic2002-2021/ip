package duke.command.taskcommand.taskquery;

import static duke.dukeutility.prettify.Prettify.prettifyTasks;
import java.util.List;
import duke.TaskManager;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandProjection extends Command {
    public CommandProjection(TaskManager taskMgr, Integer period) {
        super(ResponseType.TASK_PROJECTION,
            List.of("list", prettifyTasks(taskMgr.getTasksForNextDays(period)), period.toString()));
    }
}
