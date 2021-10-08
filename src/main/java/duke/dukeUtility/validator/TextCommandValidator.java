package duke.dukeUtility.validator;

import java.nio.file.Files;
import java.nio.file.Path;

import static duke.dukeUtility.definition.CommandPromptsAndOptions.*;
/**
 * Text Command Validation methods
 */
public class TextCommandValidator {
    public static Boolean isRequestExitLoop(String text) {
        return text.equals(PROMPT_EXIT_LOOP);
    }
    public static Boolean isRequestList(String text) {
        return text.equals(PROMPT_LIST);
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
    public static Boolean isRequestDeleteTask(String text) {
        return text.startsWith(PROMPT_DELETE_TASK);
    }
    public static Boolean isParentDirectoryValid(Path p) {
        return Files.exists(p.getParent());
    }
}