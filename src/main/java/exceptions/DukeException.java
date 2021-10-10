package exceptions;

/**
 *  The class <code>DukeException</code> is a form of <code>Throwable</code> that indicates exceptions caught by the Duke program.
 */
public class DukeException extends Exception{
    private String message;

    /**
     *  Constructor of <code>DukeException</code>.
     *
     * @param message Duke error message to be printed.
     */
    public DukeException(String message){ this.message = message; }

    @Override
    public String getMessage() {
        return message;
    }
}
