package duke.command.taskcommand.taskadd;

import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;
import duke.TaskManager;

import java.time.LocalDateTime;
import java.util.List;


public class CommandAddNewEvent extends Command {

    public CommandAddNewEvent(TaskManager taskMgr, String taskDescription, LocalDateTime from, LocalDateTime to) {
        super(ResponseType.TASK_CREATE_EVENT, List.of("create", taskDescription, taskMgr.addNewEvent(taskDescription, from, to).getTaskDescription()));
    }
}
