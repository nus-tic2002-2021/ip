package Exception;

/**
 * DukeException handles all Duke error in graceful manner.
 */

public class DukeException extends Exception {
    //no other code needed
    public DukeException(String message){
        super(message);
    }
}