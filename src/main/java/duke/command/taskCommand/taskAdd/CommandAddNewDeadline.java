package duke.command.taskCommand.taskAdd;

import duke.TaskManager;
import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

import java.util.List;


public class CommandAddNewDeadline extends Command {
    public CommandAddNewDeadline(TaskManager taskMgr, String taskDescription, String deadlineString) {
        super(ResponseType.TASK_CREATE_DEADLINE, List.of("create", taskDescription, taskMgr.addNewDeadline(taskDescription, deadlineString).getTaskDescription()));
    }
}
