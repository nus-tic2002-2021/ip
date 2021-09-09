package exceptions;

public class InvalidCommandException extends Exception {
    private static final String ERROR_PREFIX = "Invalid Command:";
    public static final String ERROR_GENERIC = "I don't know what that command means!";

    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }

    public String getErrorHeader() {
        return ERROR_PREFIX;
    }
}
