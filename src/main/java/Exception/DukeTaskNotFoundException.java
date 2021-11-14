package Exception;

/**
 * DukeTaskNotFoundException handles invalid Duke Task (e.g. when user input invalid index number).
 */

public class DukeTaskNotFoundException extends Exception {
    //no other code needed
    public DukeTaskNotFoundException(String message){
        super(message);
    }
}