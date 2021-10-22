package duke.command.taskcommand.taskadd;

import java.time.LocalDateTime;
import java.util.List;

import duke.TaskManager;
import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

public class CommandAddNewEvent extends Command {

    public CommandAddNewEvent(TaskManager taskMgr, String desc, LocalDateTime from, LocalDateTime to) {
        super(ResponseType.TASK_CREATE_EVENT, List.of(taskMgr.addNewEvent(desc, from, to).getTaskDescription()));
    }
}
