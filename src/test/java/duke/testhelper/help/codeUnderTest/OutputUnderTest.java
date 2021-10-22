package duke.testhelper.help.codeUnderTest;

import java.nio.file.Path;

public class OutputUnderTest {
    private static final String LOGO = " _                   _                                _             "
        + System.lineSeparator()
        + "| |                 | |                              | |                "
        + System.lineSeparator()
        + "| |_    __ _   ___  | | __  _ __ ___     __ _   ___  | |_    ___   _ __ "
        + System.lineSeparator()
        + "| __|  / _` | / __| | |/ / | '_ ` _ \\   / _` | / __| | __|  / _ \\ | '__|"
        + System.lineSeparator()
        + "| |_  | (_| | \\__ \\ |   <  | | | | | | | (_| | \\__ \\ | |_  |  __/ | |   "
        + System.lineSeparator()
        + " \\__|  \\__,_| |___/ |_|\\_\\ |_| |_| |_|  \\__,_| |___/  \\__|  \\___| |_|  "
        + System.lineSeparator();

    private static final String RESPONSE_TERMINATOR_UNDER_TEST = "\t\t\t\t\t\t\t\t -" + System.lineSeparator();

    public static String getExpectedOutputEntry() {
        return "Hello from" + System.lineSeparator() + LOGO;
    }

    public static String getExpectedOutputTerminate() {
        return "See you again!" + System.lineSeparator();
    }

    public static String getExpectedOutputBeginInputLoop() {
        return "How can i help you? (See docs for usage)" + System.lineSeparator();
    }

    public static String getExpectedOutputImportAttempt(Path path) {
        return "Attempting to import tasks from " + path + "." + System.lineSeparator();
    }

    public static String getExpectedOutputReadPathNotFound() {
        return "Read path not found/invalid. " + System.lineSeparator();
    }

    public static String getExpectedOutputTemplate(String text) {
        return text + System.lineSeparator() + RESPONSE_TERMINATOR_UNDER_TEST;
    }

    public static String getExpectedOutputExitInputLoop() {
        return getExpectedOutputTemplate("ok bye");
    }

    public static String getExpectedOutputAddedToDo(String taskDesc) {
        return getExpectedOutputTemplate("Added To Do: " + taskDesc);
    }

    public static String getExpectedOutputAddedDeadline(String deadlineDesc) {
        return getExpectedOutputTemplate("Added Deadline: " + deadlineDesc);
    }

    public static String getExpectedOutputAddedEvent(String eventDesc) {
        return getExpectedOutputTemplate("Added Event: " + eventDesc);
    }

    public static String getExpectedOutputList(String list) {
        return list + RESPONSE_TERMINATOR_UNDER_TEST;
    }

    public static String getExpectedOutputTaskSetCompleted(Integer taskId) {

        return getExpectedOutputTemplate("update done #" + taskId.toString());
    }

    public static String getExpectedOutputCommandUnknown() {

        return getExpectedOutputTemplate("Unknown command. . .");
    }

    public static String getExpectedOutputCommandDeleted(Integer taskId) {
        return getExpectedOutputTemplate("Task Deleted: " + taskId);
    }

    public static String getMsgUnderTestErrorSpacedKeyword() {
        String text = "Invalid parameters: java.lang.Exception: Invalid syntax. Keyword should not have spacing.";
        return getExpectedOutputTemplate(text);
    }

    public static String getMsgUnderTestErrorParseStringAsLocalDate() {
        return getExpectedOutputTemplate("Invalid parameters: java.lang.Exception: Parse as LocalDateTime failed.");
    }
}
