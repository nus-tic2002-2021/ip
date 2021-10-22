package duke.dukeExceptions;

public abstract class DukeException extends Exception {
    protected DukeException(String message) {
        super(message);
    }
}
