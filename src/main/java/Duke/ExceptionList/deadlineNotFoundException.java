package Duke.ExceptionList;

public class deadlineNotFoundException extends Exception{
    //no other code needed
    public deadlineNotFoundException(String message) {
        super(message);
    }
    public deadlineNotFoundException(){
        super();
    }
}
