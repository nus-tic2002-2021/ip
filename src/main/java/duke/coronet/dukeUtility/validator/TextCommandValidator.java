package duke.coronet.dukeUtility.validator;

import static duke.coronet.dukeUtility.definition.CommandPromptsAndOptions.*;

/**
 * Text Command Validation methods
 */
public class TextCommandValidator {
    public static Boolean isRequestExitLoop(String text) {
        return text.equals(PROMPT_EXIT);
    }
    public static Boolean isRequestList(String text) {
        return text.equals("list");
    }
    public static Boolean isRequestMarkTaskAsDone(String text) {
        return text.startsWith(PROMPT_UPDATE_DONE);
    }
    public static Boolean isRequestAddToDo(String text) {
        return text.startsWith(PROMPT_ADD_TODO);
    }

    public static Boolean isRequestAddDeadline(String text) {
        return text.startsWith(PROMPT_ADD_DEADLINE);
    }

    public static Boolean isRequestAddEvent(String text) {
        return text.startsWith(PROMPT_ADD_EVENT);
    }

}