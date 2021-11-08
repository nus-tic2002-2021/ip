package com.alexooi.duke.tasks;

import com.alexooi.duke.enums.TaskType;
import com.alexooi.duke.commands.Command;
import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import com.alexooi.duke.exceptions.InvalidDateFormatException;
import com.alexooi.duke.exceptions.InvalidFileFormatException;
import com.alexooi.duke.interfaces.Parser;
import com.alexooi.duke.utility.DateParser;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskFactory {
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_DATE = "date";
    private static final String DEADLINE_PATTERN =
            "^(?<" + KEY_DESCRIPTION + ">.+) /by (?<" + KEY_DATE + ">.+)$";
    private static final String EVENT_PATTERN =
            "^(?<" + KEY_DESCRIPTION + ">.+) /at (?<" + KEY_DATE + ">.+)$";
    private static final Parser<String, LocalDateTime> dateParser = new DateParser();

    private static LocalDateTime handleDateInput(String input) throws InvalidDateFormatException {
        try {
            return dateParser.parseInput(input);
        } catch (Exception e) {
            throw new InvalidDateFormatException();
        }
    }

    /**
     * This function takes in a command and outputs the corresponding task based on the command shown.
     * @param cmd   The command containing the keyword (Task name) and arguments (Date if applicable)
     * @return      Deadline, Event or Todo task
     * @throws InvalidCommandFormatException    If the date does is not parsable for Deadline and Event, this exception is thrown
     */
    public static Task getInstance(Command cmd) throws InvalidCommandFormatException {
        String keyword = cmd.getKeyword();
        String args = cmd.getArgs();
        if (keyword.equalsIgnoreCase(TaskType.DEADLINE.toString())) {
            Matcher deadlineMatch =
                    Pattern.compile(DEADLINE_PATTERN, Pattern.CASE_INSENSITIVE).matcher(args);
            if (!deadlineMatch.matches()) {
                throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_DEADLINE);
            }
            String description = deadlineMatch.group(KEY_DESCRIPTION);
            String dueDate = deadlineMatch.group(KEY_DATE);
            try {
                LocalDateTime dueBy = handleDateInput(dueDate);
                return new Deadline(description, dueBy);
            } catch (InvalidDateFormatException idfe) {
                throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_DEADLINE);
            }
        } else if (keyword.equalsIgnoreCase(TaskType.EVENT.toString())) {
            Matcher eventMatch = Pattern.compile(EVENT_PATTERN, Pattern.CASE_INSENSITIVE).matcher(args);
            if (!eventMatch.matches()) {
                throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_EVENT);
            }
            String description = eventMatch.group(KEY_DESCRIPTION);
            String timing = eventMatch.group(KEY_DATE);
            try {
                LocalDateTime eventTime = handleDateInput(timing);
                return new Event(description, eventTime);
            } catch (InvalidDateFormatException idfe) {
                throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_DEADLINE);
            }
        } else if (keyword.equalsIgnoreCase(TaskType.TODO.toString())) {
            if (args.length() <= 0) {
                throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_TODO);
            }
            return new Todo(args);
        }
        throw new InvalidCommandFormatException(
                "Cannot find the appropriate task to create.");
    }

    /**
     * This function parses an input string that is formatted in the save file format and returns the respective Task
     * @param s     The input string to parse into a task
     * @return      The Deadline, Event or To do Task
     * @throws InvalidFileFormatException   This exception gets thrown if the line being parsed does not match any Task.
     *                                          It should not occur unless the file has been modified externally.
     */
    public static Task getInstance(String s) throws InvalidFileFormatException {
        String[] line = s.split("\\|");
        assert line.length > 2;
        boolean doneState = line[1].equalsIgnoreCase("t");
        Task currentTask;

        String keyword = line[0];
        if (keyword.equalsIgnoreCase(TaskType.DEADLINE.toString())) {
            String description = line[2];
            String dueDate = line[3];
            try {
                LocalDateTime dueBy = handleDateInput(dueDate);
                return new Deadline(description, dueBy);
            } catch (InvalidDateFormatException idfe) {
                throw new InvalidFileFormatException(InvalidFileFormatException.ERROR_DATE);
            }
        } else if (keyword.equalsIgnoreCase(TaskType.EVENT.toString())) {
            String description = line[2];
            String timing = line[3];

            try {
                LocalDateTime eventTime = handleDateInput(timing);
                return new Event(description, eventTime);
            } catch (InvalidDateFormatException idfe) {
                throw new InvalidFileFormatException(InvalidFileFormatException.ERROR_DATE);
            }
        } else if (keyword.equalsIgnoreCase(TaskType.TODO.toString())) {
            currentTask = new Todo(line[2]);
        } else {
            throw new InvalidFileFormatException();
        }

        currentTask.setDone(doneState);
        return currentTask;
    }
}
