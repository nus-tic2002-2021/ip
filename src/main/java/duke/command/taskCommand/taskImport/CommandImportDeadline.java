package duke.command.taskCommand.taskImport;

import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;
import duke.TaskManager;
import duke.task.model.Deadline;

import java.util.List;


public class CommandImportDeadline extends Command {
    public CommandImportDeadline(Deadline deadline, TaskManager taskMgr) {
        super(ResponseType.TASK_IMPORT_DEADLINE, List.of("create", deadline.getTaskDescription(), taskMgr.importDeadline(deadline).getTaskDescription()));
    }
}
