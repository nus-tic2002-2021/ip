package duke.command.taskcommand.taskimport;

import java.util.List;

import duke.TaskManager;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;
import duke.task.model.Event;


public class CommandImportEvent extends Command {
    /**
     * Import a event to a task manager
     *
     * @param event   event
     * @param taskMgr task manager
     */
    public CommandImportEvent(Event event, TaskManager taskMgr) {
        super(ResponseType.TASK_IMPORT_EVENT,
            List.of("create", event.getTaskDescription(), taskMgr.importEvent(event).getTaskDescription()));
    }

    public String getResponse() {
        return null;
    }
}
