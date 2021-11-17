package com.alexooi.duke.commands;

import com.alexooi.duke.enums.CommandType;
import com.alexooi.duke.interfaces.OutputFormat;
import com.alexooi.duke.tasks.Task;
import com.alexooi.duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.ServiceLoader;
import java.util.regex.Pattern;

public class HelpCommand extends Command {
    private final String REGEX_PATTERN = "^(help)$";

    public HelpCommand() {
        setCommandType(CommandType.HELP);
    }

    public HelpCommand(String keyword) {
        super(keyword);
        setCommandType(CommandType.HELP);
    }

    @Override
    public String execute(TaskList tasks, OutputFormat<Task> prompt) {
        ServiceLoader<Command> commandLoader = ServiceLoader.load(Command.class);
        ArrayList<String> promptInput = new ArrayList<>();
        for (Command cmd : commandLoader) {
            ArrayList<String> commandFormat = new ArrayList<>();
            cmd.getCommandFormat().forEach(commandFormat::add);
            promptInput.add(String.join(" : ", commandFormat));
        }
        return prompt.help(promptInput);
    }

    @Override
    public Pattern getRegexPattern() {
        return Pattern.compile(REGEX_PATTERN, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public Command build() {
        return new HelpCommand(getKeyword());
    }
}
