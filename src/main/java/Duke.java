
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello! I'm Duke By Justin\nWhat can I do for you?\n" + logo);

        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            System.out.print("You: ");
            line = in.nextLine();

            if (line.equals("bye")) {
                line = "Bye. Hope to see you again soon!";
                System.out.println("Duke: " + line);
                break;
            }

            else System.out.println("Duke: " + line);

        }

    }
}
