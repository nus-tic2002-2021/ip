package duke.coronet.command.taskCommand.taskAdd;

import duke.coronet.command.Command;
import duke.coronet.dukeUtility.enums.ResponseType;
import duke.coronet.manager.TaskManager;

import java.time.LocalDateTime;
import java.util.List;


public class CommandAddNewDeadline extends Command {
    public CommandAddNewDeadline(TaskManager taskMgr, String taskDescription, LocalDateTime deadline) {
        super(ResponseType.TASK_CREATE_DEADLINE, List.of("create", taskDescription, taskMgr.addNewDeadline(taskDescription, deadline).getTaskDescription()));
    }
}
