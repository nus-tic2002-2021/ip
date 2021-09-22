public class MissReqException extends Exception{
    String message;
    MissReqException(String req){
        message = String.format("☹ OOPS!!! The description of a %s cannot be empty.", req);

    }
    public String getMessage(){
        return  message;
    }
}