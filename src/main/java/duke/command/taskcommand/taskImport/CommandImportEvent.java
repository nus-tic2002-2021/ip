package duke.command.taskcommand.taskImport;

import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;
import duke.TaskManager;
import duke.task.model.Event;

import java.util.List;


public class CommandImportEvent extends Command {
    public CommandImportEvent(Event event, TaskManager taskMgr) {
        super(ResponseType.TASK_IMPORT_EVENT, List.of("create", event.getTaskDescription(), taskMgr.importEvent(event).getTaskDescription()));
    }
}
