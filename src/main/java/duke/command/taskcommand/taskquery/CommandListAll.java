package duke.command.taskcommand.taskquery;

import static duke.dukeutility.prettify.Prettify.prettifyTaskMgr;

import java.util.List;

import duke.TaskManager;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandListAll extends Command {
    public CommandListAll(TaskManager taskMgr) {
        super(ResponseType.TASK_LIST_ALL, List.of("list", prettifyTaskMgr(taskMgr)));
    }
}
