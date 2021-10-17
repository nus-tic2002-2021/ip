package Duke.exception;

public class EmptyDescriptionException extends DukeException{
    public EmptyDescriptionException(String message) {
        super(message);
    }
    /*Prints the following
        printer.separator();
        System.out.printf("     ☹ OOPS!!! The description of a %s cannot be empty.\n", taskType);
        printer.separator();
    */
}
