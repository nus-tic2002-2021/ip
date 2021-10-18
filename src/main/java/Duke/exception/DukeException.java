package Duke.exception;

public class DukeException extends RuntimeException{
    /**
     * A base class for self-defined exceptions
     *
     * @param message   the pre-defined message
     *
     */
    public DukeException(String message) {
        super(message);
    }


    public DukeException() {}
}
