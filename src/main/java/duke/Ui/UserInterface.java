package duke.Ui;

import duke.command.CommandResult;

import java.util.Scanner;

public class UserInterface {

    Scanner s;
    public UserInterface() {
        this.s = new Scanner(System.in);
    }

    public String getInput() {
        return s.nextLine();
    }


    public String getResponse(CommandResult result) {
        return result.getRespondToUser();
    }

    public void printRespond(CommandResult result) {
        System.out.println(result.getRespondToUser());
    }

    public void goodBye() {
        System.out.println("Bye");
    }


    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?");
    }

    public void logo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye :)");
    }

}
