package duke.action;

public class Parser {

    public Parser() {

    }

    public String processUserCommand(String userInput) {

        assert userInput != null : "There should be user input in processUserCommand";

        if (userInput.equals("bye")) {
            return "bye";
        } else if (userInput.equals("list")) {
            return "list";
        } else if (userInput.equals("set")) {
            return "set";
        } else if (userInput.equals("info")) {
            return "info";
        } else if (userInput.equals("save")) {
            return "save";
        }

        String userInputStartWith = getFirstWord(userInput);

        switch (userInputStartWith) {
        case "done":
            return "done";
        case "todo":
            return "todo";
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
