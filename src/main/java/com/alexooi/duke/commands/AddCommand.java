package com.alexooi.duke.commands;

import com.alexooi.duke.enums.CommandType;
import com.alexooi.duke.tasks.Task;
import com.alexooi.duke.tasks.TaskFactory;
import com.alexooi.duke.tasks.TaskList;
import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import com.alexooi.duke.interfaces.OutputFormat;

import java.util.regex.Pattern;

public class AddCommand extends Command {
    private final String REGEX_PATTERN = "^(\\bdeadline\\b|\\bevent\\b|\\btodo\\b)\\s?(.*)$";

    public AddCommand() {
        super();
        setCommandType(CommandType.ADD);
    }

    public AddCommand(String keyword, String args) {
        super(keyword, args);
        setCommandType(CommandType.ADD);
    }

    @Override
    public Pattern getRegexPattern() {
        return Pattern.compile(REGEX_PATTERN, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public AddCommand build() {
        return new AddCommand(getKeyword(), getArgs());
    }

    @Override
    public String execute(TaskList tasks, OutputFormat<Task> prompt) throws InvalidCommandFormatException {
        Task newTask = TaskFactory.getInstance(this);
        tasks.add(newTask);

        return prompt.add(newTask, tasks.size());
    }
}
