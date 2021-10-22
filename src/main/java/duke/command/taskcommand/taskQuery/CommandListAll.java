package duke.command.taskcommand.taskQuery;

import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;
import duke.TaskManager;

import java.util.List;

import static duke.dukeUtility.prettify.Prettify.prettifyTaskMgr;

public class CommandListAll extends Command {
    public CommandListAll(TaskManager taskMgr) {
        super(ResponseType.TASK_LIST_ALL, List.of("list", prettifyTaskMgr(taskMgr)));
    }
}