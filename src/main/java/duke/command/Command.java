package duke.command;

import duke.task.Task;
import duke.task.TaskList;
/**
 * Represents a command
 */
public abstract class Command {
    public TaskList taskList;
    /**
     * Executes the command and returns the result.
     */

    public abstract CommandResult execute();

    /**
     * Supplies the data that command will operate on
     * @param taskList the single taskList used in Duke
     */

    public void inputData(TaskList taskList) {
        this.taskList = taskList;
    }

    public String addTaskSuccess(Task task) {
        return "Got it. I have added the task!\n" + task
                + "Now you have " + taskList.getSize() + " tasks in the list.";

    }
}
