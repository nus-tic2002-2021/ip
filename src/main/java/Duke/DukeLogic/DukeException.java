package Duke.DukeLogic;

/**
 * An exception class that represents the errors that are thrown when using duke.
 */
public class DukeException extends Exception{
    protected String ErrMsg;

    /**
     * DukeException constructor that creates a DukeException
     * with an empty error message.
     */
    public DukeException() {
        this.ErrMsg = "";
    }

    /**
     * DukeException constructor takes in a custom error message.
     * @param invalid
     */
    public DukeException(String invalid) {
        this.ErrMsg = invalid;
    }

    /**
     * Prints the custom error message of a DukeException if any.
     */
    public void printErrMsg() {
        System.out.println(ErrMsg);
    }
}
