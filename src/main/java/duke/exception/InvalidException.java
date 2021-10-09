package duke.exception;

public class InvalidException extends Exception{
    public InvalidException(){}

    public String getMessage(){
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
