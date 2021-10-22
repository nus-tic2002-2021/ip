package duke.command.taskCommand.taskQuery;

import duke.TaskManager;
import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

import java.util.List;

import static duke.dukeUtility.prettify.Prettify.prettifyTasks;

public class CommandListTasksWithKeyword extends Command {
    public CommandListTasksWithKeyword(TaskManager taskMgr, String keyword) {
        super(ResponseType.TASK_LIST, List.of("list", prettifyTasks(taskMgr.getTasksWithWord(keyword))));
    }
}