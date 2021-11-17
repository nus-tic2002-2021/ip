package com.alexooi.duke.commands;

import com.alexooi.duke.enums.CommandType;
import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import com.alexooi.duke.interfaces.OutputFormat;
import com.alexooi.duke.interfaces.Parser;
import com.alexooi.duke.tasks.Deadline;
import com.alexooi.duke.tasks.Event;
import com.alexooi.duke.tasks.Task;
import com.alexooi.duke.tasks.TaskList;
import com.alexooi.duke.utility.DateParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ViewScheduleCommand extends Command {
    private final String REGEX_PATTERN = "^(\\bview\\b) (.*)$";
    final Parser<String, LocalDateTime> parser;

    public ViewScheduleCommand() {
        setCommandType(CommandType.VIEW_SCHEDULE);
        this.parser = new DateParser();
    }

    public ViewScheduleCommand(String keyword, String args) {
        super(keyword, args);
        setCommandType(CommandType.VIEW_SCHEDULE);
        this.parser = new DateParser();
    }

    @Override
    public String execute(TaskList tasks, OutputFormat<Task> prompt) throws InvalidCommandFormatException {
        ArrayList<Task> taskList = tasks.get();
        try {
            LocalDateTime dateTime = parser.parseInput(getArgs());

            List<Task> filteredList = taskList.stream().filter((element) -> {
                boolean isScheduled = false;
                switch (element.getType()) {
                case EVENT:
                    Event event = (Event) element;
                    if (event.getTiming().toLocalDate().equals(dateTime.toLocalDate())) {
                        isScheduled = true;
                    }
                    break;
                case DEADLINE:
                    Deadline deadline = (Deadline) element;
                    if (deadline.getDueDate().toLocalDate().equals(dateTime.toLocalDate())) {
                        isScheduled = true;
                    }
                    break;
                }
                return isScheduled;
            }).collect(Collectors.toList());

            return prompt.viewSchedule(filteredList, dateTime.toLocalDate());
        } catch (Exception e) {
            throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_VIEW_SCHEDULE);
        }
    }

    @Override
    public Pattern getRegexPattern() {
        return Pattern.compile(REGEX_PATTERN, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public ViewScheduleCommand build() throws InvalidCommandFormatException {
        if (!parser.isValidInput(getArgs())) {
            throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_VIEW_SCHEDULE);
        }
        return new ViewScheduleCommand(getKeyword(), getArgs());
    }
}
