package duke.coronet.command.taskCommand.taskQuery;

import duke.coronet.command.Command;
import duke.coronet.dukeUtility.enums.ResponseType;
import duke.coronet.manager.TaskManager;

import java.util.List;

import static duke.coronet.dukeUtility.prettify.Prettify.prettifyTaskMgr;

public class CommandListAll extends Command {
    public CommandListAll(TaskManager taskMgr) {
        super(ResponseType.TASK_LIST_ALL, List.of("list", prettifyTaskMgr(taskMgr)));
    }
}
