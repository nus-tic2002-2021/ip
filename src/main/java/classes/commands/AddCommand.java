package classes.commands;

import classes.enums.CommandType;
import classes.tasks.Task;
import classes.tasks.TaskFactory;
import classes.tasks.TaskList;
import exceptions.InvalidCommandFormatException;
import interfaces.Promptable;

public class AddCommand extends Command {
    public AddCommand(CommandType type, String keyword) {
        super(type, keyword);
    }

    public AddCommand(CommandType type, String keyword, String args) {
        super(type, keyword, args);
    }

    @Override
    public String execute(TaskList tasks, Promptable<Task> prompt) throws InvalidCommandFormatException {
        Task newTask = TaskFactory.getInstance(this);
        tasks.add(newTask);

        return prompt.add(newTask, tasks.size());
    }
}
