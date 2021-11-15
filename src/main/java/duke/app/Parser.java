package duke.app;

import duke.command.*;
import duke.exceptions.InvalidUserInputException;
import duke.task.TaskFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;

/**
 * Check and parse the user input and throw exceptions if the input is invalid.
 */
public class Parser {

    public enum validActions {
        ADD, LIST, DONE, DELETE, BYE, DATE
    }

    private String userInput;
    private Map<String, String> parsedInput;

    public void setUserInput(String userInput) {
        this.userInput = userInput;
        parsedInput = new HashMap<>();
    }

    /**parse user command to be passed to commandFactory
     * @return a command object created by <code>CommandFactory</code>
     * @throws InvalidUserInputException if command is not valid.
     */
    public AbstractCommand parseCommand() throws InvalidUserInputException {
        validActions validAction = getAction();
        return CommandFactory.create(validAction);
    }

    /**
     * Parse and check user input
     * - classify into different commands, i.e. list, add, date, delete, bye, done
     * - for add task, the first word must be event/todo/deadline.
     * - for all the task, description cannot be empty
     *  - for event/deadline, time of format "yyyy-mm-dd hh:mm" must be provided
     * @return parsed user input
     * @throws InvalidUserInputException if the command is invalid
     */
    public Map<String, String> parseAndCheckInput() throws InvalidUserInputException {
        try {
            parseValidAction();
            parseUserInput();
            return parsedInput;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidUserInputException("Oops the task description/time is empty");
        }
    }

    public validActions getAction() {
        return validActions.valueOf(parsedInput.get("Action"));
    }

    public static LocalDateTime parseDateTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * format LocalDateTime object using a user-friendly format for printing
     * @param time, a LocalDateTime object
     * @return formatted time, String
     */
    public static String formatDatetimeToString(LocalDateTime time) {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(time);
    }

    private void parseValidAction() throws InvalidUserInputException {
        String action = userInput.split(" ")[0].trim().toUpperCase();
        for (validActions a : validActions.values()) {
            if (a.name().equals(action)) {
                parsedInput.put("Action", action);
            }
        }
        for (TaskFactory.validTaskTypes type : TaskFactory.validTaskTypes.values()) {
            if (type.name().equals(action)) {
                parsedInput.put("Action", validActions.ADD.name());
                parsedInput.put("TaskType", action);
            }
        }
        if (!parsedInput.containsKey("Action")) {
            throw new InvalidUserInputException("Command invalid, please enter a valid command");
        }
    }

    private void parseUserInput() throws InvalidUserInputException {

        if (validActions.DATE.name().equals(parsedInput.get("Action"))) {
            String time = userInput.split("/")[0].split(" ", 2)[1];
            parseTime(time);
            return;
        }
        if (!validActions.LIST.name().equals(parsedInput.get("Action")) &&
                !validActions.BYE.name().equals(parsedInput.get("Action"))) {
            parseNameOrIndex();
        }
        if (parsedInput.containsKey("TaskType") && !parsedInput.get("TaskType").equals("TODO")) {
            String time = userInput.split("/")[1].substring(2).trim();
            parseTime(time);
        }
    }

    private void parseNameOrIndex() {
        String name = userInput.split("/")[0].split(" ", 2)[1];

        parsedInput.put("NameOrIndex", name);
    }

    private void parseTime(String time) throws InvalidUserInputException {
        //allow empty time?
        try {
            Parser.parseDateTime(time);
            parsedInput.put("Time", time);
        } catch (Exception e) {
            throw new InvalidUserInputException("DateTime format should be yyyy-MM-dd HH:mm");
        }
    }
}
