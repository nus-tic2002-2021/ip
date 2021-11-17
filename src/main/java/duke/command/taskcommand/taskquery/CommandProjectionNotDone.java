package duke.command.taskcommand.taskquery;

import static duke.dukeutility.prettify.Prettify.prettifyTasks;

import java.util.List;

import duke.TaskManager;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandProjectionNotDone extends Command {
    public CommandProjectionNotDone(TaskManager taskMgr, Integer period) {
        super(ResponseType.TASK_PROJECTION_NOT_DONE,
            List.of("list", prettifyTasks(taskMgr.getTasksForNextDaysNotDone(period)), period.toString()));
    }

    public String getResponse() {
        return "Incomplete tasks for the next " + this.getArgs().get(2) + " days: " + System.lineSeparator() +
            this.getArgs().get(1);
    }
}
