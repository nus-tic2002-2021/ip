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
        ADD, LIST, DONE, DELETE, BYE, REMIND, FIND, TAG
    }

    private String userInput;
    private Map<String, String> parsedInput;

    public void setUserInput(String userInput) {
        this.userInput = userInput;
        parsedInput = new HashMap<>();
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

    /**parse user command to be passed to commandFactory
     * @return a command object created by CommandFactory
     * @throws InvalidUserInputException if command is not valid.
     */
    public AbstractCommand parseCommand() throws InvalidUserInputException {
        validActions validAction = getAction();
        return CommandFactory.create(validAction);
    }

    public validActions getAction() {
        return validActions.valueOf(parsedInput.get("Action"));
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
            assert parsedInput.get("Action") != null;
            return parsedInput;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidUserInputException("Oops the task description/time is empty");
        }
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

        if (!validActions.LIST.name().equals(parsedInput.get("Action")) &&
                !validActions.BYE.name().equals(parsedInput.get("Action"))) {
            parseNameOrIndex();
        }
        if (parsedInput.containsKey("TaskType") && !parsedInput.get("TaskType").equals("TODO")) {
            String time = userInput.split("/")[1].substring(2).trim();
            parseTime(time);
        }
        if (parsedInput.get("Action").equals(validActions.TAG.name())) {
            parseTag();
        }
    }

    private void parseNameOrIndex() throws InvalidUserInputException {
        String nameOrIndex = userInput.split("/")[0].split(" ", 2)[1].trim().split("#")[0].trim();
        if ("".equals(nameOrIndex)) {
            throw new InvalidUserInputException("Oops the task description/index is empty");
        }
        checkValidIndex(nameOrIndex);
        parsedInput.put("NameOrIndex", nameOrIndex);
    }

    private void checkValidIndex(String nameOrIndex) throws InvalidUserInputException {
        String[] actionWithIndex = new String[]{validActions.DONE.name(), validActions.DELETE.name(),
                validActions.REMIND.name(), validActions.TAG.name()};
        for (String action: actionWithIndex) {
            if (action.equals(parsedInput.get("Action"))) {
                try {
                    Integer.parseInt(nameOrIndex);
                } catch (NumberFormatException e) {
                    throw new InvalidUserInputException("done/delete/remind/tag command must follow by an integer index");
                }
            }
        }
    }

    private void parseTime(String time) throws InvalidUserInputException {
        try {
            Parser.parseDateTime(time);
            parsedInput.put("Time", time);
        } catch (Exception e) {
            throw new InvalidUserInputException("DateTime format should be yyyy-MM-dd HH:mm");
        }
    }

    private void parseTag() throws InvalidUserInputException {
        if (!userInput.contains("#")) {
            throw new InvalidUserInputException("use #tagname to indicate a tag, e.g. #fun");
        }
        String tag = userInput.split("#")[1].trim();
        parsedInput.put("Tag", tag);
    }
}
