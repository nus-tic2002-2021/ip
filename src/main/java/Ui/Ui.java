package Ui;

/**
 * Handler for all UI related function such as printing messages and exceptions
 */

public class Ui {

    public static void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0) ;
    }

    public static void showException() {
        System.out.println("unexpected command");
    }

    public static void showException(String e) {
        System.out.println("unexpected error: " + e);
    }
}
