package Duke;

public class DukeException extends Exception{
    protected String ErrMsg;

    public DukeException() {
        this.ErrMsg = "";
    }

    public DukeException(String invalid) {
        this.ErrMsg = invalid;
    }

    public void printErrMsg() {
        System.out.println(ErrMsg);
    }
}
