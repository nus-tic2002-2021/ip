package duke.command.taskcommand.taskquery;

import static duke.dukeutility.prettify.Prettify.prettifyTasks;
import java.util.List;
import duke.TaskManager;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandProjectionAll extends Command {
    public CommandProjectionAll(TaskManager taskMgr, Integer period) {
        super(ResponseType.TASK_PROJECTION_ALL,
            List.of("list", prettifyTasks(taskMgr.getTasksForNextDaysAll(period)), period.toString()));
    }

    public String getResponse() {
        return "All tasks for the next " + this.getArgs().get(2) + " days: " + System.lineSeparator() +
            this.getArgs().get(1);
    }
}
