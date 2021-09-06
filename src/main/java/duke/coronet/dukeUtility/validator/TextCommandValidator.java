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
}