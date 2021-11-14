package app;

import exceptions.InvalidUserInputException;
import task.TaskFactory;

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

    public Map<String, String> getParsedInput() {
        return parsedInput;
    }

    public void parseUserInput() {

        if (!validActions.LIST.name().equals(parsedInput.get("Action")) &&
        !validActions.BYE.name().equals(parsedInput.get("Action")))
            parseNameOrIndex();
        if (parsedInput.containsKey("TaskType") && !parsedInput.get("TaskType").equals("TODO")) {
            parseTime();
        }
    }

    public void parseValidAction() throws InvalidUserInputException {
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
