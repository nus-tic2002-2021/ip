package exception;

/**
 * Wraps exception. Helps to check if exception has been accounted for.
 * Catches exception and throws DukeException.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public DukeException(String message, Throwable cause) {
        super(message,cause);
    }
}
