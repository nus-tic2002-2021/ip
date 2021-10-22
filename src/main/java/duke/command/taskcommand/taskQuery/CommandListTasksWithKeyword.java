package duke.command.taskcommand.taskQuery;

import static duke.dukeUtility.prettify.Prettify.prettifyTasks;
import java.util.List;
import duke.TaskManager;
import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

public class CommandListTasksWithKeyword extends Command {
    public CommandListTasksWithKeyword(TaskManager taskMgr, String keyword) {
        super(ResponseType.TASK_LIST_ALL, List.of("list", prettifyTasks(taskMgr.getTasksWithWord(keyword))));
    }
}