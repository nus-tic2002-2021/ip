package duke.action;

public class Parser {

    public Parser() {

    }

    public String processUserCommand(String userInput) {

        if (userInput.equals("bye")) {
            return "bye";
        }

        if (userInput.equals("list")) {
            return "list";
        }

        if (userInput.equals("set")){
            return "set";
        }

        String userInputStartWith = getFirstWord(userInput);

        switch (userInputStartWith) {
        case "done":
            return "done";
        case "todo":
            return "todo";
        case "save":
            return "save";
        case "load":
            return "load";
        case "find":
            return "find";
        case "event":
            return "event";
        case "delete":
            return "delete";
        case "deadline":
            return "deadline";
        default:
            return "invalid";
        }
    }

    private String getFirstWord(String firstWord) {

        int index = firstWord.indexOf(' ');

        if (index > -1) {
            return firstWord.substring(0, index).trim();
        } else {
            return "invalid";
        }
    }
}
