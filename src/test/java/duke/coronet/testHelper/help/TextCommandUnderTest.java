package duke.coronet.testHelper.help;

public class TextCommandUnderTest {
    public static final String PROMPT_UNDER_TEST_EXIT_LOOP = "bye";
    public static final String PROMPT_UNDER_TEST_LIST = "list";
    public static final String PROMPT_UNDER_TEST_MARK_AS_DONE = "done ";

    private static String singleArgumentCommand(String text) {
        return text + System.lineSeparator();
    }
    public static String generateTextCommandExit(String invoke) {
        return singleArgumentCommand(invoke);
    }
    public static String generateTextCommandFullTextAsEcho(String text) {
        return singleArgumentCommand(text);
    }
    public static String generateTextCommandFullTextAsNewToDoDescription(String text) {
        return singleArgumentCommand(text);
    }
    public static String generateTextCommandList(String invoke) {
        return singleArgumentCommand(invoke);
    }
    public static String generateTextCommandSetCompleted(String invoke, Integer taskId) {
        return invoke + taskId + System.lineSeparator();
    }
}