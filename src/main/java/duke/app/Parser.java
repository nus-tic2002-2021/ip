package duke.app;

import duke.command.*;
import duke.exceptions.InvalidUserInputException;
import duke.task.TaskFactory;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public enum validActions {
        ADD, LIST, DONE, DELETE, BYE
    }

    private String userInput;
    private Map<String, String> parsedInput;

    public void setUserInput(String userInput) {
        this.userInput = userInput;
        parsedInput = new HashMap<>();
    }

    public AbstractCommand parseCommand() throws InvalidUserInputException {
        validActions validAction = getAction();
        return CommandFactory.create(validAction);
    }

    public Map<String, String> parseAndCheckInput() throws InvalidUserInputException {
        try {
            parseValidAction();
            parseUserInput();
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
            throw new InvalidUserInputException("input invalid, please enter a valid command");
        }
    }

    private void parseUserInput() {

        if (!validActions.LIST.name().equals(parsedInput.get("Action")) &&
                !validActions.BYE.name().equals(parsedInput.get("Action"))) {
            parseNameOrIndex();
        }
        if (parsedInput.containsKey("TaskType") && !parsedInput.get("TaskType").equals("TODO")) {
            parseTime();
        }
    }

    private void parseNameOrIndex() {
        String name = userInput.split("/")[0].split(" ", 2)[1];

        parsedInput.put("NameOrIndex", name);
    }

    private void parseTime() {
        //allow empty time?
        String time = userInput.split("/")[1].substring(2);
        parsedInput.put("Time", time);
    }

    public validActions getAction() {
        return validActions.valueOf(parsedInput.get("Action"));
    }
}
