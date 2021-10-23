package Duke.exception;


public class TimeParseException extends DukeException{
    /**
     * Thrown when error occured while parsing time
     *
     * Prints the following
     * separator();
     * System.out.printf("     There seems to have problem with your time format [%s]\n", message);
     * separator();
     *
     * @param message   the pre-defined message
     *
     */
    public TimeParseException(String message) {
        super(message);
    }
}
