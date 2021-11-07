package com.alexooi.duke.tasks;

import com.alexooi.duke.enums.TaskType;
import com.alexooi.duke.commands.Command;
import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import com.alexooi.duke.exceptions.InvalidFileFormatException;
import com.alexooi.duke.interfaces.DateParser;
import com.alexooi.duke.utility.InputDateParser;

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
    private static final DateParser dateParser = new InputDateParser();

    /**
     * This function takes in a command and
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
            if (deadlineMatch.matches()) {
                String description = deadlineMatch.group(KEY_DESCRIPTION);
                String dueDate = deadlineMatch.group(KEY_DATE);
                LocalDateTime dueBy = dateParser.parseInput(dueDate);
                if (dueBy == null) {
                    throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_DEADLINE);
                }
                return new Deadline(description, dueBy);
            } else {
                throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_DEADLINE);
            }
        } else if (keyword.equalsIgnoreCase(TaskType.EVENT.toString())) {
            Matcher eventMatch = Pattern.compile(EVENT_PATTERN, Pattern.CASE_INSENSITIVE).matcher(args);
            if (eventMatch.matches()) {
                String description = eventMatch.group(KEY_DESCRIPTION);
                String timing = eventMatch.group(KEY_DATE);
                LocalDateTime eventTime = dateParser.parseInput(timing);
                if (eventTime == null) {
                    throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_DEADLINE);
                }
                return new Event(description, eventTime);
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
            LocalDateTime dueBy = dateParser.parseInput(dueDate);
            if (dueBy == null) {
                throw new InvalidFileFormatException(InvalidFileFormatException.ERROR_DATE);
            }
            currentTask = new Deadline(description, dueBy);
        } else if (keyword.equalsIgnoreCase(TaskType.EVENT.toString())) {
            String description = line[2];
            String timing = line[3];
            LocalDateTime eventTime = dateParser.parseInput(timing);
            if (eventTime == null) {
                throw new InvalidFileFormatException(InvalidFileFormatException.ERROR_DATE);
            }
            currentTask = new Event(description, eventTime);
        } else if (keyword.equalsIgnoreCase(TaskType.TODO.toString())) {
            currentTask = new Todo(line[2]);
        } else {
            throw new InvalidFileFormatException();
        }

        currentTask.setDone(doneState);
        return currentTask;
    }
}
