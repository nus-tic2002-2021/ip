public class InvalidFormatException extends Exception {
    protected String errorFormat;

    InvalidFormatException (String errorMessage){
        super(errorMessage);
    }

}
