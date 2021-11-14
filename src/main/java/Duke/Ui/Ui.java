package Duke.Ui;

public class Ui {
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final String logoMessage = logo + "\nHello! I'm Duke.Duke. \nWhat can I do for you?";

    private final String breakLines = "____________________________________________________________";

    private String print(){
        return logoMessage;
    }

    //Printing divider
    public void printBreak() {
        System.out.println(breakLines);
    }

    //Printing goodbye message
    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    //Printing the count of tasks in the list.
    public void printTotalTask(int counter) {
        System.out.println("Now you have " + counter + " tasks in the list.");
    }

    //Printing start message
    public void showStartMessage(){
        System.out.println(print());
    }

    //Printing Loading Error message
    public void showLoadingError() {
        System.out.println("Sorry file is not found. We will save a new copy.");
    }
}
