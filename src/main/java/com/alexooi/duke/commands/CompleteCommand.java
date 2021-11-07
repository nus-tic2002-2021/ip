package com.alexooi.duke.commands;

import com.alexooi.duke.enums.CommandType;
import com.alexooi.duke.tasks.Task;
import com.alexooi.duke.tasks.TaskList;
import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import com.alexooi.duke.interfaces.OutputFormat;

import java.util.regex.Pattern;

public class CompleteCommand extends Command {
    private final String REGEX_PATTERN = "^\\b(done)\\b (.+)$";

    public CompleteCommand() {
        setCommandType(CommandType.COMPLETE);
    }

    public CompleteCommand(String keyword, String args) {
        super(keyword, args);
        setCommandType(CommandType.COMPLETE);
    }

    @Override
    public Pattern getRegexPattern() {
        return Pattern.compile(REGEX_PATTERN, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public CompleteCommand build() {
        return new CompleteCommand(getKeyword(), getArgs());
    }

    @Override
    public String execute(TaskList tasks, OutputFormat<Task> prompt) throws InvalidCommandFormatException {
        int idx = Integer.parseInt(getArgs()) - 1;
        Task doneTask = tasks.getTask(idx);
        doneTask.setDone(!doneTask.getDone());
        return prompt.done(doneTask, doneTask.getDone());
    }
}
