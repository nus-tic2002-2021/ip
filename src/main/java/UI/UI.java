package UI;
public class UI{

    private static ReturnMessages returnMessage = new ReturnMessages();

    public void show(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void showLoadingError(){
        returnMessage.exception_feedback_loadingError();
    }
}
