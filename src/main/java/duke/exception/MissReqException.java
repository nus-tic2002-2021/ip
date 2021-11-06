package duke.exception;

public class MissReqException extends Exception{
    String message;
    public MissReqException(String req){
        message = String.format("☹ OOPS!!! The description of a %s cannot be empty.", req);

    }
    public String getMessage(){
        return  message;
    }
}
