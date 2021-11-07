package com.alexooi.duke.commands;

import com.alexooi.duke.enums.CommandType;
import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import com.alexooi.duke.interfaces.OutputFormat;
import com.alexooi.duke.tasks.Task;
import com.alexooi.duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    private final String REGEX_PATTERN = "^(\\bfind\\b) (.*)$";

    public FindCommand() {
        setCommandType(CommandType.FIND);
    }

    public FindCommand(String keyword, String args) {
        super(keyword, args);
        setCommandType(CommandType.FIND);
    }

    @Override
    public String execute(TaskList tasks, OutputFormat<Task> prompt) throws InvalidCommandFormatException {
        ArrayList<Task> taskList = tasks.get();

        List<Task> filteredList = taskList.stream().filter((task) -> task.getDescription().contains(getArgs())).collect(Collectors.toList());

        return prompt.find(filteredList);
    }

    @Override
    public Pattern getRegexPattern() {
        return Pattern.compile(REGEX_PATTERN, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public Command build() throws InvalidCommandFormatException {
        return new FindCommand(getKeyword(), getArgs());
    }
}
