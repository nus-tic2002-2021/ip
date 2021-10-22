package duke.dukeUtility.validator;

import static duke.dukeUtility.definition.CommandPromptsAndOptions.PROMPT_ADD_DEADLINE;
import static duke.dukeUtility.definition.CommandPromptsAndOptions.PROMPT_ADD_EVENT;
import static duke.dukeUtility.definition.CommandPromptsAndOptions.PROMPT_ADD_TODO;
import static duke.dukeUtility.definition.CommandPromptsAndOptions.PROMPT_DELETE_TASK;
import static duke.dukeUtility.definition.CommandPromptsAndOptions.PROMPT_EXIT_LOOP;
import static duke.dukeUtility.definition.CommandPromptsAndOptions.PROMPT_FIND_BY_KEYWORD_DESCRIPTION;
import static duke.dukeUtility.definition.CommandPromptsAndOptions.PROMPT_LIST;
import static duke.dukeUtility.definition.CommandPromptsAndOptions.PROMPT_SAVE;
import static duke.dukeUtility.definition.CommandPromptsAndOptions.PROMPT_UPDATE_DONE;
import java.nio.file.Files;
import java.nio.file.Path;

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

    public static Boolean isRequestFind(String text) {
        return text.startsWith(PROMPT_FIND_BY_KEYWORD_DESCRIPTION);
    }

    public static Boolean isParentDirectoryValid(Path p) {
        return Files.exists(p.getParent());
    }

    public static Boolean isRequestSave(String text) {
        return text.equals(PROMPT_SAVE);
    }
}