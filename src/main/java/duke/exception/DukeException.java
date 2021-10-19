package duke.exception;

import java.io.FileNotFoundException;

/**
 * A <code>DukeException</code> object for exceptions related to Duke.
 * Extends the <code>Exception</code> class.
 */
public class DukeException extends Exception {

    private String message;

    /**
     * Constructs DukeException with no message specified.
     */
    public DukeException() {}

    /**
     * Constructs DukeException with this message.
     *
     * @param message DukeException message.
     */
    public DukeException(String message) { this.message = message; }

    @Override
    public String getMessage() {
        return message;
    }

}
