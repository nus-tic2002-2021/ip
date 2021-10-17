package Duke.exception;

public class EmptyTaskListException extends DukeException{
    public EmptyTaskListException(String message) {
        super(message);
    }
    /*Prints the following
        printer.separator();
        System.out.println("     ☹ OOPS!!! I'm sorry, the list is empty ^_^");
        printer.separator();
    */
}
