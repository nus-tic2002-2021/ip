package classes.commands;

import classes.enums.CommandType;
import classes.tasks.Task;
import classes.tasks.TaskList;
import exceptions.InvalidCommandFormatException;
import interfaces.Promptable;

public class ListCommand extends Command {
    public ListCommand(CommandType type, String keyword) {
        super(type, keyword);
    }

    public ListCommand(CommandType type, String keyword, String args) {
        super(type, keyword, args);
    }

    @Override
    public String execute(TaskList tasks, Promptable<Task> prompt) throws InvalidCommandFormatException {
        return prompt.list(tasks.get());
    }
}
