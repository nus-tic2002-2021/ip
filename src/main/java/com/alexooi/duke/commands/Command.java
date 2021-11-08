package com.alexooi.duke.commands;

import com.alexooi.duke.enums.CommandType;
import com.alexooi.duke.tasks.Task;
import com.alexooi.duke.tasks.TaskList;
import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import com.alexooi.duke.interfaces.OutputFormat;

import java.util.ArrayList;
import java.util.regex.Pattern;

public abstract class Command {
    private String keyword;
    private String args;
    private CommandType commandType;

    public Command() {

    }

    public Command(String keyword) {
        this.setKeyword(keyword);
    }

    public Command(String keyword, String args) {
        this(keyword);
        this.setArgs(args);
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public Iterable<String> getCommandFormat() {
        ArrayList<String> commandFormat = new ArrayList<>();
        commandFormat.add(getCommandType().getFormat());
        if (getCommandType().getNotes() != null) {
            commandFormat.add(getCommandType().getNotes());
        }
        return commandFormat;
    }

    public abstract String execute(TaskList tasks, OutputFormat<Task> prompt) throws InvalidCommandFormatException;

    public abstract Pattern getRegexPattern();

    public abstract Command build() throws InvalidCommandFormatException;

    public boolean isExit() {
        return false;
    }
}
