package duke;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Ui ui;

    public static void main(String[] args) {
        String text;
        Scanner in = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();

        Ui.Welcome();

        //takes in user input
        text = in.nextLine();

        //use while loop to program running
        while(!text.equalsIgnoreCase("bye")){
            //print the current list by calling printList method, if user inputs "list"
            try {
                Task.tasks(text, tasks);
            } catch (DukeException e){
                Ui.errorMessage(e);
            } finally {
                text = in.nextLine();
            }
        }

        Ui.goodBye();
        System.exit(0);
    }
}
