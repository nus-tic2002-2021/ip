package com.alexooi.duke.commands;

import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import com.alexooi.duke.interfaces.OutputFormat;
import com.alexooi.duke.tasks.Task;
import com.alexooi.duke.tasks.TaskList;

import java.util.regex.Pattern;

public class ArchiveCommand extends Command {
    private final String REGEX_PATTERN = "^(\\barchive\\b)\\s+(\\d+|all)$";
    private final String ARGS_ALL = "all";

    public ArchiveCommand() {

    }

    public ArchiveCommand(String keyword, String args) {
        super(keyword, args);
    }

    @Override
    public String execute(TaskList tasks, OutputFormat<Task> prompt) throws InvalidCommandFormatException {
        String output;
        if (getArgs().equalsIgnoreCase(ARGS_ALL)) {
            tasks.archive();
            output = prompt.archive(tasks.get());
            tasks.removeAll();
        } else {
            int idx = Integer.parseUnsignedInt(getArgs()) - 1;
            if (idx > tasks.size() + 1) {
                throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_ARCHIVE_NO_SUCH_TASK);
            }
            tasks.archive(idx);
            Task archived = tasks.remove(idx);
            output = prompt.archive(archived);
        }
        return output;
    }

    @Override
    public Pattern getRegexPattern() {
        return Pattern.compile(REGEX_PATTERN, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public Command build() throws InvalidCommandFormatException {
        return new ArchiveCommand(getKeyword(), getArgs());
    }
}
