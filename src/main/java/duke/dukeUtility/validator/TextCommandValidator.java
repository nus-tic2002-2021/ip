package duke.dukeUtility.validator;

import static duke.dukeUtility.definition.CommandPromptsAndOptions.*;
/**
 * Text Command Validation methods
 */
public class TextCommandValidator {
    public static Boolean isRequestExitLoop(String text) {
        return text.equals(PROMPT_EXIT);
    }
    public static Boolean isRequestMarkTaskAsDone(String text) {
        return text.startsWith(PROMPT_UPDATE_DONE);
    }

    public static Boolean isRequestList(String text) {
        return text.equals(PROMPT_LIST);
    }
}