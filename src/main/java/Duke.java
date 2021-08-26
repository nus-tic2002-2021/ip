
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\nHello! I'm Duke By Justin\nWhat can I do for you?\n" + logo);

        String[] listTasks = new String[100];
        String newTask;
        int count = 0;

        while (true) {

            Scanner in = new Scanner(System.in);
            System.out.print("You: ");
            newTask = in.nextLine();

            if (newTask.equals("bye")) {
                System.out.println("Duke: Bye. Hope to see you again soon!");
                break;
            }

            else if (newTask.equals("list")) {

                for (int i = 0; i < count; i++) {
                    System.out.println( i+1 + ". " + listTasks[i] );
                }

                System.out.print("\n");

            }

            else {
                System.out.println("Added: " + newTask + "\n");
                listTasks[count] = newTask;
                count++;
            }

        }

    }
}
