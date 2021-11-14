package Duke.ExceptionList;

public class CommandNotFoundException extends Exception{
    //no other code needed
    public CommandNotFoundException(String message) {
        super(message);
    }
    public CommandNotFoundException(){
        super();
    }
}
