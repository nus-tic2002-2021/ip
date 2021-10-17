package Duke.exception;

public class TaskNotFoundException extends DukeException{
    public TaskNotFoundException(String message) {
        super(message);
    }
    /*Prints the following
        printer.separator();
        System.out.println("     ☹ OOPS!!! I'm sorry, please key in by Tasks.Task ID instead of [%s]", message);
        printer.separator();
    */
}

