package duke.command.taskcommand.taskadd;

import java.time.LocalDateTime;
import java.util.List;
import duke.TaskManager;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;
import duke.task.model.Event;

public class CommandAddNewEvent extends Command {

    public CommandAddNewEvent(TaskManager tm, String desc, LocalDateTime from, LocalDateTime to) {
        super(ResponseType.TASK_CREATING_IN_PROGRESS,
            null);

        Event ev = tm.addNewEvent(desc, from, to);

        this.setArgs(List.of("create", ev.getTaskId().toString(), ev.getTaskDescription()));
        this.setResponseType(ResponseType.TASK_CREATE_EVENT);
    }

    public String getResponse() {
        String id = this.getArgs().get(1);
        String desc = this.getArgs().get(2);
        return "Added Event [id #" + id + "]: " + desc + System.lineSeparator();
    }

}
