package com.alexooi.duke.commands;

import com.alexooi.duke.enums.CommandType;
import com.alexooi.duke.tasks.Task;
import com.alexooi.duke.tasks.TaskList;
import com.alexooi.duke.interfaces.OutputFormat;

import java.util.regex.Pattern;

public class ExitCommand extends Command {
    private final String REGEX_PATTERN = "^(bye)$";

    public ExitCommand() {
        setCommandType(CommandType.EXIT);
    }

    public ExitCommand(String keyword) {
        super(keyword);
        setCommandType(CommandType.EXIT);
    }

    @Override
    public Pattern getRegexPattern() {
        return Pattern.compile(REGEX_PATTERN, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public ExitCommand build() {
        return new ExitCommand(getKeyword());
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList tasks, OutputFormat<Task> prompt) {
        return prompt.exit();
    }
}
