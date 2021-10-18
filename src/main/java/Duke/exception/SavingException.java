package Duke.exception;

public class SavingException extends DukeException{
    /**
     * Thrown when there is problem saving the current task list
     *
     * Prints the following
     *         separator();
     *         System.out.println("     OOPS!!! Exception caught while saving >_<|||");
     *         separator();
     *
     * @param message   the pre-defined message
     *
     */
    public SavingException(String message) {
        super(message);
    }

}
