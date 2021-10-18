package Duke.exception;

public class UnknownSyntaxException extends DukeException{
    /**
     * Thrown when user input does not categorize under any of the pre-defined message.
     *
     * Prints the following
     *      printer.separator();
     *      System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
     *      printer.separator();
     *
     * @param message   the pre-defined message
     *
     */
    public UnknownSyntaxException(String message) {
        super(message);
    }
}
