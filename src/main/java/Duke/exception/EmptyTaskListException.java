package Duke.exception;


public class EmptyTaskListException extends DukeException{

    /**
     * Thrown when the task list is empty
     *
     * Prints the following
     *         printer.separator();
     *         System.out.println("     ☹ OOPS!!! I'm sorry, the list is empty ^_^");
     *         printer.separator();
     *
     * @param message Printing message overload
     */
    public EmptyTaskListException(String message) {
        super(message);
    }
    /*
    */
}
