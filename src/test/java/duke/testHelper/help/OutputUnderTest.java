package duke.testHelper.help;

import java.nio.file.Path;

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
    private static String PREFIX_ADDED_DEADLINE = "Added Deadline: ";
    private static String PREFIX_ADDED_EVENT = "Added Event: ";
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

    public static String getMsgUnderTestAttemptImport(Path path){
        return "Attempting to import tasks from " + path + "." + System.lineSeparator();
    }
    public static String getMsgUnderTestReadPathNotFound(){
        return "Read path not found/invalid. " + System.lineSeparator();
    }
    public static String getMsgUnderTestExitLoop() {
        return "ok bye" + System.lineSeparator() + RESPONSE_TERMINATOR_UNDER_TEST;
    }
    public static String getMsgUnderTestResponseToDoAdded(String taskDesc) {
        return PREFIX_ADDED_TO_DO + taskDesc + System.lineSeparator() + RESPONSE_TERMINATOR_UNDER_TEST;
    }

    public static String getMsgUnderTestResponseEventAdded(String eventDesc){
        return PREFIX_ADDED_EVENT + eventDesc + System.lineSeparator() + RESPONSE_TERMINATOR_UNDER_TEST;
    }

    public static String getMsgUnderTestResponseDeadlineAdded(String deadlineDesc){
        return PREFIX_ADDED_DEADLINE + deadlineDesc + System.lineSeparator() + RESPONSE_TERMINATOR_UNDER_TEST;
    }
    public static String getMsgUnderTestResponseListAll(String list) {
        return list + RESPONSE_TERMINATOR_UNDER_TEST;
    }
    public static String getMsgUnderTestResponseTaskSetCompleted(Integer taskId) {
        return "update done #" + taskId.toString() + System.lineSeparator() + RESPONSE_TERMINATOR_UNDER_TEST;
    }

    public static String getMsgUnderTestUnknownRequest(){
        return "Unknown command. . ." + System.lineSeparator() + RESPONSE_TERMINATOR_UNDER_TEST;
    }

    public static String getMsgUnderTestResponseTaskDeleted(Integer taskId){
        return "Task Deleted: " + taskId + System.lineSeparator() + RESPONSE_TERMINATOR_UNDER_TEST;

    }

    public static String getMsgUnderTestErrorParseStringAsLocalDate(){

        return "Invalid parameters: java.lang.Exception: Parse as LocalDateTime failed." + System.lineSeparator() + RESPONSE_TERMINATOR_UNDER_TEST;
    }
}
