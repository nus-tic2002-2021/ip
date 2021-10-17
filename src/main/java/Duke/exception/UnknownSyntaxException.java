package Duke.exception;

public class UnknownSyntaxException extends DukeException{
    public UnknownSyntaxException(String message) {
        super(message);
    }
    /*Prints the following
        printer.separator();
        System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printer.separator();
    */
}
