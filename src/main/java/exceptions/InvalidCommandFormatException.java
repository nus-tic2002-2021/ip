package exceptions;

public class InvalidCommandFormatException extends Throwable {
    private static final String ERROR_PREFIX = "Invalid Command Format:";
    public static final String ERROR_DEADLINE =
            "classes.tasks.Deadline should be in the format: deadline <description> /by <date>.";
    public static final String ERROR_EVENT =
            "classes.tasks.Event should be in the format: event <description> /at <date>.";
    public static final String ERROR_TODO =
            "classes.tasks.Todo should be in the format: todo <description>.";

    private String errorMessage;

    public InvalidCommandFormatException(String errorMessage) {
        super(errorMessage);
    }

    public String getErrorHeader() {
        return ERROR_PREFIX;
    }

    public String toString() {
        return getMessage();
    }
}
