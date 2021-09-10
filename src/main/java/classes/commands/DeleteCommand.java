package classes.commands;

import classes.enums.CommandType;
import classes.tasks.Task;
import classes.tasks.TaskList;
import exceptions.InvalidCommandFormatException;
import interfaces.Promptable;

public class DeleteCommand extends Command {
    public DeleteCommand(CommandType type, String keyword) {
        super(type, keyword);
    }

    public DeleteCommand(CommandType type, String keyword, String args) {
        super(type, keyword, args);
    }

    @Override
    public String execute(TaskList tasks, Promptable<Task> prompt) throws InvalidCommandFormatException {
        int idx = Integer.parseInt(getArgs()) - 1;
        Task removedTask = tasks.remove(idx);
        return prompt.remove(removedTask, tasks.size());
    }
}
