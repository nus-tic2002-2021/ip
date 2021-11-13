package dukeMain.exceptions;

public class DukeException extends Exception{
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
//    public Exception.DukeException(String message,Throwable cause) {
//        super(message,cause);
//    }
    public DukeException(String message) {
        super(message);
    }
    public DukeException(){
        super();
    }
}
