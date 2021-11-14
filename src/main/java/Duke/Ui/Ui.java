package Duke.Ui;

public class Ui {
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private String logoMessage = logo + "\nHello! I'm Duke.Duke. \nWhat can I do for you?";

    private String breaklines = "____________________________________________________________";

    public void printBreak() {
        System.out.println(breaklines);
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printTotalTask(int counter) {
        System.out.println("Now you have " + counter + " tasks in the list.");
    }

    private String print(){
        return logoMessage;
    }

    public void showStartMessage(){
        System.out.println(print());
    }

    public void showLoadingError() {
        System.out.println("Sorry file is not found. We will save a new copy.");
    }
}
