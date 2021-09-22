package duke.testHelper.help;

public class OutputUnderTest {
    private static String LOGO = " _                   _                                _             "
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

    private static String PREFIX_ECHO = "Echoed after you: ";
    private static String PREFIX_ADDED_TO_DO = "Added To Do: ";
    private static String RESPONSE_TERMINATOR_UNDER_TEST = "\t\t\t\t\t\t\t\t -" + System.lineSeparator();

    public static String getMsgUnderTestEntry() {
        return "Hello from" + System.lineSeparator() + LOGO;
    }
    public static String getMsgUnderTestTerminate() {
        return "See you again!" + System.lineSeparator();
    }
    public static String getMsgUnderTestBeginInputLoop() {
        return "How can i help you? (See docs for usage)" + System.lineSeparator();
    }

    public static String getMsgUnderTestExitLoop() {
        return "ok bye" + System.lineSeparator() + RESPONSE_TERMINATOR_UNDER_TEST;
    }
    public static String getMsgUnderTestResponseTaskAdded(String taskDesc) {
        return PREFIX_ADDED_TO_DO + taskDesc + System.lineSeparator() + RESPONSE_TERMINATOR_UNDER_TEST;
    }
    public static String getMsgUnderTestResponseListAll(String list) {
        return list + RESPONSE_TERMINATOR_UNDER_TEST;
    }

}
