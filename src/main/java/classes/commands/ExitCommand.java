package classes.commands;

import classes.enums.CommandType;
import classes.tasks.Task;
import classes.tasks.TaskList;
import exceptions.InvalidCommandFormatException;
import interfaces.Promptable;

public class ExitCommand extends Command {
    public ExitCommand(CommandType type, String keyword) {
        super(type, keyword);
    }

    @Override
    public String execute(TaskList tasks, Promptable<Task> prompt) throws InvalidCommandFormatException {
        return prompt.exit();
    }
}
