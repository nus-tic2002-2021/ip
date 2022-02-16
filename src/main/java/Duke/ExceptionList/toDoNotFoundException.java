package Duke.ExceptionList;

public class toDoNotFoundException extends Exception{
    //no other code needed
    public toDoNotFoundException(String message) {
        super(message);
    }
    public toDoNotFoundException(){
        super();
    }
}
