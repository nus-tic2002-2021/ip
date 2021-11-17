package duke.command.taskcommand.taskquery;

import static duke.dukeutility.prettify.Prettify.getScanDuplicatesDescription;

import java.util.List;

import duke.TaskManager;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandScanDuplicateDescriptions extends Command {
    public CommandScanDuplicateDescriptions(TaskManager taskMgr) {
        super(ResponseType.SCAN_DUPLICATE_DESCRIPTION,
            List.of("stat", getScanDuplicatesDescription(taskMgr.getDuplicateDescriptionsAsArray())));
    }

    public String getResponse() {
        return "Duplicates               \"[Description]\":[...(id,type)] " + System.lineSeparator() +
            this.getArgs().get(1);
    }
}
