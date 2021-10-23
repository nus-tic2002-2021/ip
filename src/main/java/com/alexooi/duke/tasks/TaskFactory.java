package com.alexooi.duke.tasks;

import com.alexooi.duke.enums.TaskType;
import com.alexooi.duke.commands.Command;
import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import com.alexooi.duke.exceptions.InvalidFileFormatException;
import com.alexooi.duke.interfaces.DateParser;
import com.alexooi.duke.utility.InputDateParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskFactory {
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_DATE = "date";
    private static final String DEADLINE_PATTERN =
            "^(?<" + KEY_DESCRIPTION + ">.+) /by (?<" + KEY_DATE + ">.+)$";
    private static final String EVENT_PATTERN =
            "^(?<" + KEY_DESCRIPTION + ">.+) /at (?<" + KEY_DATE + ">.+)$";
    private static final DateParser dateParser = new InputDateParser();

    public static Task getInstance(Command cmd) throws InvalidCommandFormatException {
        String keyword = cmd.getKeyword();
        String args = cmd.getArgs();
        if (keyword.equalsIgnoreCase(TaskType.DEADLINE.toString())) {
            Matcher deadlineMatch =
                    Pattern.compile(DEADLINE_PATTERN, Pattern.CASE_INSENSITIVE).matcher(args);
            if (deadlineMatch.matches()) {
                String description = deadlineMatch.group(KEY_DESCRIPTION);
                String dueDate = deadlineMatch.group(KEY_DATE);
                return new Deadline(description, dueDate, dateParser);
            } else {
                throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_DEADLINE);
            }
        } else if (keyword.equalsIgnoreCase(TaskType.EVENT.toString())) {
            Matcher eventMatch = Pattern.compile(EVENT_PATTERN, Pattern.CASE_INSENSITIVE).matcher(args);
            if (eventMatch.matches()) {
                String description = eventMatch.group(KEY_DESCRIPTION);
                String timing = eventMatch.group(KEY_DATE);
                return new Event(description, timing, dateParser);
            } else {
                throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_EVENT);
            }
        } else if (keyword.equalsIgnoreCase(TaskType.TODO.toString())) {
            if (args.length() > 0) {
                return new Todo(args);
            } else {
                throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_TODO);
            }
        }
        throw new InvalidCommandFormatException(
                "Cannot find the appropriate task to create.");
    }

    public static Task getInstance(String s) throws InvalidFileFormatException {
        String[] line = s.split("\\|");
        boolean doneState = line[1].equalsIgnoreCase("t");
        Task currentTask;

        String keyword = line[0];
        if (keyword.equalsIgnoreCase(TaskType.DEADLINE.toString())) {
            String description = line[2];
            String dueDate = line[3];
            currentTask = new Deadline(description, dueDate, dateParser);
        } else if (keyword.equalsIgnoreCase(TaskType.EVENT.toString())) {
            String description = line[2];
            String timing = line[3];
            currentTask = new Event(description, timing, dateParser);
        } else if (keyword.equalsIgnoreCase(TaskType.TODO.toString())) {
            currentTask = new Todo(line[2]);
        } else {
            throw new InvalidFileFormatException();
        }

        currentTask.setDone(doneState);
        return currentTask;
    }
}
