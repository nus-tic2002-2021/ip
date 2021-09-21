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
    public static String getMsgUnderTestEntry() {
        return "Hello from" + System.lineSeparator() + LOGO;
    }
    public static String getMsgUnderTestTerminate() {
        return "See you again!" + System.lineSeparator();
    }
}
