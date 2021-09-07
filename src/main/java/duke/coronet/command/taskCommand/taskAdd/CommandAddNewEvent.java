package duke.coronet.command.taskCommand.taskAdd;

import duke.coronet.command.Command;
import duke.coronet.dukeUtility.enums.ResponseType;
import duke.coronet.manager.TaskManager;

import java.time.LocalDateTime;
import java.util.List;


public class CommandAddNewEvent extends Command {

    public CommandAddNewEvent(TaskManager taskMgr, String taskDescription, LocalDateTime from, LocalDateTime to) {
        super(ResponseType.TASK_CREATE_EVENT, List.of("create", taskDescription, taskMgr.addNewEvent(taskDescription, from, to).getTaskDescription()));
    }
}
