package duke.action;

/**
 * Make sense of user input and return the user command
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class Parser {

    public Parser() {

    }

    /**
     * Convert date from String to LocalDate
     *
     * @param userInput String
     * @return String that describe the command specified by user
     */
    public String processUserCommand(String userInput) {

        if (userInput.isEmpty()) {
            return "invalid";
        }

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
        case "undone":
            return "undone";
        case "deadline":
            return "deadline";
        default:
            return "invalid";
        }
    }

    /**
     * Convert date from String to LocalDate
     *
     * @param firstWord String
     * @return String that represents the first word of a line of string
     */
    private String getFirstWord(String firstWord) {

        int index = firstWord.indexOf(' ');

        if (index > -1) {
            return firstWord.substring(0, index).trim();
        } else {
            return "invalid";
        }
    }

}
