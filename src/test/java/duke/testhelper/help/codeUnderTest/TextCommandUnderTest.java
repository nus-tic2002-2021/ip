package duke.testhelper.help.codeUnderTest;

public class TextCommandUnderTest {
    public static final String PROMPT_UNDER_TEST_EXIT_LOOP = "bye";
    public static final String PROMPT_UNDER_TEST_LIST = "list";
    public static final String PROMPT_UNDER_TEST_MARK_AS_DONE = "done ";
    public static final String PROMPT_UNDER_TEST_ADD_TO_DO = "todo ";

    public static final String PROMPT_UNDER_TEST_ADD_DEADLINE = "deadline ";
    public static final String DELIMITER_DEADLINE_DEADLINE = " /by ";

    public static final String PROMPT_UNDER_TEST_ADD_EVENT = "event ";
    public static final String DELIMITER_EVENT_FROM = " /at ";
    public static final String DELIMITER_EVENT_TO = "-";

    public static final String PROMPT_UNDER_TEST_DELETE_TASK = "delete ";

    public static final String PROMPT_UNDER_TEST_SAVE = "save";

    public static final String PROMPT_UNDER_TEST_FIND = "find ";

    private static String singleArgumentCommand(String text) {
        return text + System.lineSeparator();
    }

    public static String generateTextCommandExit(String invoke) {
        return singleArgumentCommand(invoke);
    }

    public static String generateTextCommandRandom(String text) {
        return singleArgumentCommand(text);
    }

    public static String generateTextCommandList(String invoke) {
        return singleArgumentCommand(invoke);
    }

    public static String generateTextCommandSetCompleted(String invoke, Integer taskId) {
        return invoke + taskId + System.lineSeparator();
    }

    public static String generateTextCommandLineAddToDo(String invoke, String description) {
        return invoke + description + System.lineSeparator();
    }

    public static String generateTextCommandLineAddDeadline(String invoke, String description,
                                                            String deadlineStringDelimiter, String deadlineString) {
        return invoke + description + deadlineStringDelimiter + deadlineString + System.lineSeparator();
    }

    public static String generateTextCommandLineAddEvent(String invoke, String eventDescription,
                                                         String addEventScheduleDelimiter, String from,
                                                         String fromToDelimiter, String to) {
        return invoke + eventDescription + addEventScheduleDelimiter + from + fromToDelimiter + to +
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
}