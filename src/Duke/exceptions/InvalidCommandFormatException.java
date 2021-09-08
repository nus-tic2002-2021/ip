package Duke.exceptions;

public class InvalidCommandFormatException extends Throwable {
    private static final String ERROR_PREFIX = "Invalid Command Format:\n";
    public static final String ERROR_DEADLINE = "Deadline should be in the format: deadline <description> /by <date>.";
    public static final String ERROR_EVENT = "Event should be in the format: event <description> /at <date>.";
    public static final String ERROR_TODO = "Todo should be in the format: todo <description>.";

    private String errorMessage;

    public InvalidCommandFormatException(String errorMessage) {
        setErrorMessage(errorMessage);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return ERROR_PREFIX + getErrorMessage();
    }
}