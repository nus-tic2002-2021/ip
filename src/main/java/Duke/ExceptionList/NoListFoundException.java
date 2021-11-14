package Duke.ExceptionList;

public class NoListFoundException extends Exception {
    //no other code needed
    public NoListFoundException(String message) {
        super(message);
    }

    public NoListFoundException() {
        super();
    }
}
