package com.alexooi.duke.commands;

import com.alexooi.duke.enums.CommandType;
import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import com.alexooi.duke.interfaces.DateParser;
import com.alexooi.duke.interfaces.OutputFormat;
import com.alexooi.duke.tasks.Deadline;
import com.alexooi.duke.tasks.Event;
import com.alexooi.duke.tasks.Task;
import com.alexooi.duke.tasks.TaskList;
import com.alexooi.duke.utility.InputDateParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ViewScheduleCommand extends Command {
    private final String REGEX_PATTERN = "^(\\bview\\b) (.*)$";
    DateParser parser;

    public ViewScheduleCommand() {
        setCommandType(CommandType.VIEW_SCHEDULE);
        this.parser = new InputDateParser();
    }

    public ViewScheduleCommand(String keyword, String args) {
        super(keyword, args);
        setCommandType(CommandType.VIEW_SCHEDULE);
        this.parser = new InputDateParser();
    }

    @Override
    public String execute(TaskList tasks, OutputFormat<Task> prompt) throws InvalidCommandFormatException {
        ArrayList<Task> taskList = tasks.get();
        LocalDateTime dateTime = parser.parseInput(getArgs());
        if (dateTime == null) {
            throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_VIEW_SCHEDULE);
        }

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
    }

    @Override
    public Pattern getRegexPattern() {
        return Pattern.compile(REGEX_PATTERN, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public ViewScheduleCommand build() throws InvalidCommandFormatException {
        if (!parser.isDate(getArgs(), DateTimeFormatter.ISO_DATE)) {
            throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_VIEW_SCHEDULE);
        }
        return new ViewScheduleCommand(getKeyword(), getArgs());
    }
}
