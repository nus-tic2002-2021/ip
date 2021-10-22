package duke.command.taskcommand.taskQuery;

import static duke.dukeUtility.prettify.Prettify.prettifyTaskMgr;
import java.util.List;
import duke.TaskManager;
import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

public class CommandListAll extends Command {
    public CommandListAll(TaskManager taskMgr) {
        super(ResponseType.TASK_LIST_ALL, List.of("list", prettifyTaskMgr(taskMgr)));
    }
}