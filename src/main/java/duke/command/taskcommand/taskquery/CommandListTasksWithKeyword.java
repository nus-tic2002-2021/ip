package duke.command.taskcommand.taskquery;

import static duke.dukeutility.prettify.Prettify.prettifyTasks;

import java.util.List;

import duke.TaskManager;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandListTasksWithKeyword extends Command {
    public CommandListTasksWithKeyword(TaskManager taskMgr, String keyword) {
        super(ResponseType.TASK_LIST_FIND, List.of("list", prettifyTasks(taskMgr.getTasksWithWord(keyword)), keyword));
    }
}
