package duke.testhelper.help.codeundertest;

public class TextCommandUnderTest {
    public static final String PROMPT_UNDER_TEST_EXIT_LOOP = "bye";
    public static final String PROMPT_UNDER_TEST_LIST = "list";
    public static final String PROMPT_UNDER_TEST_STATS_ALL = "stats all";
    public static final String PROMPT_UNDER_TEST_SCAN_DUPLICATE_DESCRIPTION = "scan:duplicates";
    public static final String PROMPT_UNDER_TEST_PROJECTION = "projection ";

    public static final String PROMPT_UNDER_TEST_MARK_AS_DONE = "done ";
    public static final String PROMPT_UNDER_TEST_MARK_AS_INCOMPLETE = "undone ";

    public static final String PROMPT_UNDER_TEST_ADD_TO_DO = "todo ";

    public static final String PROMPT_UNDER_TEST_ADD_DEADLINE = "deadline ";
    public static final String DELIMITER_DEADLINE_DEADLINE = " /by ";

    public static final String PROMPT_UNDER_TEST_ADD_EVENT = "event ";
    public static final String DELIMITER_EVENT_EVENT = " /at ";
    public static final String DELIMITER_EVENT_TO = "-";

    public static final String PROMPT_UNDER_TEST_DELETE_TASK = "delete ";

    public static final String PROMPT_UNDER_TEST_SAVE = "save";

    public static final String PROMPT_UNDER_TEST_FIND = "find ";

    private static String singleArgumentCommand(String text) {
        return text + System.lineSeparator();
    }

    public static String generateTextCommandExit() {
        return singleArgumentCommand(PROMPT_UNDER_TEST_EXIT_LOOP);
    }

    public static String generateTextCommandStatsAll() {
        return singleArgumentCommand(PROMPT_UNDER_TEST_STATS_ALL);
    }

    public static String generateTextCommandRandom(String text) {
        return singleArgumentCommand(text);
    }

    public static String generateTextCommandList() {
        return singleArgumentCommand(PROMPT_UNDER_TEST_LIST);
    }

    public static String generateTextCommandSetCompleted(Integer taskId) {
        return PROMPT_UNDER_TEST_MARK_AS_DONE + taskId + System.lineSeparator();
    }

    public static String generateTextCommandSetIncomplete(Integer taskId) {
        return PROMPT_UNDER_TEST_MARK_AS_INCOMPLETE + taskId + System.lineSeparator();
    }
    public static String generateTextCommandScanDuplicateDescription() {
        return PROMPT_UNDER_TEST_SCAN_DUPLICATE_DESCRIPTION + System.lineSeparator();
    }
    public static String generateTextCommandLineAddToDo(String description) {
        return PROMPT_UNDER_TEST_ADD_TO_DO + description + System.lineSeparator();
    }

    public static String generateTextCommandLineAddDeadline(String description,
                                                            String deadlineString) {
        return PROMPT_UNDER_TEST_ADD_DEADLINE + description + DELIMITER_DEADLINE_DEADLINE + deadlineString +
            System.lineSeparator();
    }

    public static String generateTextCommandLineAddEvent(String desc,
                                                         String from,
                                                         String to) {
        return PROMPT_UNDER_TEST_ADD_EVENT + desc + DELIMITER_EVENT_EVENT + from + DELIMITER_EVENT_TO + to +
            System.lineSeparator();
    }

    public static String generateTextCommandDeleteTaskByTaskId(String invoke, Integer taskId) {
        return invoke + taskId + System.lineSeparator();
    }

    public static String generateTextCommandSave(String invoke) {
        return singleArgumentCommand(invoke);
    }

    public static String generateTextCommandFindKeywordInDescription(String invoke, String keyword) {
        return invoke + keyword + System.lineSeparator();
    }

    public static String generateTextCommandProjection(Integer days) {
        return PROMPT_UNDER_TEST_PROJECTION + days + System.lineSeparator();
    }
}
