package duke.command.taskcommand.taskquery;

import static duke.dukeutility.prettify.Prettify.getStatisticsAll;

import java.util.List;

import duke.TaskManager;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandStatsAll extends Command {
    public CommandStatsAll(TaskManager taskMgr) {
        super(ResponseType.TASK_STATS_ALL, List.of("stat", getStatisticsAll(taskMgr.getAllAsArray())));
    }

    public String getResponse() {
        return "Task Summary " + System.lineSeparator() + this.getArgs().get(1);
    }
}
