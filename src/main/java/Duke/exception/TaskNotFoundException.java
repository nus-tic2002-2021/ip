package Duke.exception;

public class TaskNotFoundException extends DukeException{
    /**
     * Thrown when failed to identify task, either given wrong information or task does not exist.
     *
     * Prints the following
     *         printer.separator();
     *         System.out.println("     ☹ OOPS!!! I'm sorry, please key in by Tasks.Task ID instead of [%s]", message);
     *         printer.separator();
     *
     * @param message   the pre-defined message
     *
     */
    public TaskNotFoundException(String message) {
        super(message);
    }
}

