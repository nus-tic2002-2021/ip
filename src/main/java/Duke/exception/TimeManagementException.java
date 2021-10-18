package Duke.exception;

public class TimeManagementException extends DukeException{
    /**
     * Thrown when date-time not given for deadline/event task.
     *
     * Prints the following
     *         printer.separator();
     *         System.out.println("     ☹ HEY!!! Please give a due date for deadlines and events. Let's have some sense of time");
     *         printer.separator();
     *
     *
     */
    public TimeManagementException() {
    }
}
