package duke.coronet.testHelper.help;

public class TextCommandUnderTest {
    public static final String PROMPT_UNDER_TEST_EXIT_LOOP = "bye";
    private static String singleArgumentCommand(String text) {
        return text + System.lineSeparator();
    }
    public static String generateTextCommandExit(String invoke) {
        return singleArgumentCommand(invoke);
    }
    public static String generateTextCommandFullTextAsEcho(String text) {
        return singleArgumentCommand(text);
    }
}