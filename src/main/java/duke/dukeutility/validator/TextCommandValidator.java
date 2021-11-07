package duke.dukeutility.validator;

import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_ADD_DEADLINE;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_ADD_EVENT;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_ADD_TODO;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_DELETE_TASK;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_EXIT_LOOP;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_FIND_BY_KEYWORD_DESCRIPTION;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_LIST;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_LIST_ONE;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_PROJECTION_ALL;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_PROJECTION_NOT_DONE;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_SAVE;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_SCAN_DUPLICATE_DESCRIPTION;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_STATISTICS_ALL;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_UPDATE_DONE;
import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_UPDATE_NOT_DONE;
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

    public static Boolean isRequestStatisticsAll(String text) {
        return text.equals(PROMPT_STATISTICS_ALL);
    }

    public static Boolean isRequestMarkTaskAsDone(String text) {
        return text.startsWith(PROMPT_UPDATE_DONE);
    }

    public static Boolean isRequestMarkTaskAsIncomplete(String text) {
        return text.startsWith(PROMPT_UPDATE_NOT_DONE);
    }

    public static Boolean isRequestScanDuplicates(String text) {
        return text.equals(PROMPT_SCAN_DUPLICATE_DESCRIPTION);
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

    public static Boolean isRequestProjectionAll(String text) {
        return text.startsWith(PROMPT_PROJECTION_ALL);
    }
    public static Boolean isRequestProjectionNotDone(String text) {
        return text.startsWith(PROMPT_PROJECTION_NOT_DONE);
    }

    public static Boolean isParentDirectoryValid(Path p) {
        return Files.exists(p.getParent());
    }

    public static Boolean isRequestSave(String text) {
        return text.equals(PROMPT_SAVE);
    }

    public static Boolean isRequestSee(String text) {
        return text.startsWith(PROMPT_LIST_ONE);
    }
}
