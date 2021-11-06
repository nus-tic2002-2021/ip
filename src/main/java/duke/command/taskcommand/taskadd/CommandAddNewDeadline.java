package duke.command.taskcommand.taskadd;

import java.time.LocalDateTime;
import java.util.List;
import duke.TaskManager;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;
import duke.task.model.Deadline;

public class CommandAddNewDeadline extends Command {
    /**
     * Task manager will add a new deadline to its collection with provided parameters
     *
     * @param tm       task manager
     * @param desc     of deadline
     * @param deadline of deadline
     */
    public CommandAddNewDeadline(TaskManager tm, String desc, LocalDateTime dl) {
        super(ResponseType.TASK_CREATING_IN_PROGRESS,
            null);

        Deadline deadline = tm.addNewDeadline(desc, dl);
        this.setArgs(List.of("create", deadline.getTaskId().toString(), deadline.getTaskDescription()));
        this.setResponseType(ResponseType.TASK_CREATE_DEADLINE);
    }

    public String getResponse() {
        String id = this.getArgs().get(1);
        String desc = this.getArgs().get(2);
        return "Added Deadline [id #" + id + "]: " + desc + System.lineSeparator();
    }
}
