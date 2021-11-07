package exception;

/**
 * The class extends the exceptions from IllegalArgumentException
 */
public class DukeException extends IllegalArgumentException{
    public DukeException(String s) {
        super(s);
    }
}