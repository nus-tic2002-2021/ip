package classes.commands;

import classes.enums.CommandType;
import classes.tasks.Task;
import classes.tasks.TaskList;
import exceptions.InvalidCommandFormatException;
import interfaces.Promptable;

public class CompleteCommand extends Command {
    public CompleteCommand(CommandType type, String keyword) {
        super(type, keyword);
    }

    public CompleteCommand(CommandType type, String keyword, String args) {
        super(type, keyword, args);
    }

    @Override
    public String execute(TaskList tasks, Promptable<Task> prompt) throws InvalidCommandFormatException {
        int idx = Integer.parseInt(getArgs()) - 1;
        Task doneTask = tasks.getTask(idx);
        doneTask.setDone(!doneTask.getDone());
        return prompt.done(doneTask);
    }
}
