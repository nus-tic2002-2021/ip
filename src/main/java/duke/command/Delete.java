package duke.command;

import duke.command.Command;
import duke.command.CommandResult;
import duke.task.Task;

public class Delete extends Command {
    public static final String COMMAND_WORD = "delete";
    int taskNumToDelete;


    Delete(String s) {
        this.taskNumToDelete = Integer.parseInt(s[1]);
    }
    @Override
    public CommandResult execute() {
        Task removeTask = taskList.deleteTasks(taskNumToDelete);
        return new CommandResult(Message(removeTask));
    }
    public String Message(Task removeTask) {
        return "Noted! I've removed this task:\n"
                + removeTask + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.";
    }
}
