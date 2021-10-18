package Duke.exception;

public class EmptyDescriptionException extends DukeException{

    /**
     * Thrown when user did not give a description for the task
     *
     * Prints the following
     *         printer.separator();
     *         System.out.printf("     ☹ OOPS!!! The description of a %s cannot be empty.\n", taskType);
     *         printer.separator();
     *
     * @param message   the pre-defined message
     *
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }

}
