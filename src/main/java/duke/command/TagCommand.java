package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * A <code>TagCommand</code> object for the execution of the tag command.
 * Extends the <code>Command</code> class.
 */
public class TagCommand extends Command {

    private int taskId;
    private String tagDesc;

    /**
     * Constructs TagCommand with this task id and tag description.
     *
     * @param taskId The task id to be tagged.
     * @param tagDesc The tag description.
     */
    public TagCommand(int taskId, String tagDesc) {
        this.taskId = taskId;
        if (tagDesc.equalsIgnoreCase("delete")) {
            this.tagDesc = "";
        } else {
            this.tagDesc = tagDesc;
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String task = taskList.setTag(taskId, tagDesc);
        storage.setTaskList(taskList.getTaskList());
        if (tagDesc.isEmpty()) {
            ui.printUnTaggedTask(task);
        } else {
            ui.printTaggedTask(task);
        }
    }

}
