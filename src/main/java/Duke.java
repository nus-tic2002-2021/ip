import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke ");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String line = "";

        while(true) {

            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if(line.equalsIgnoreCase("bye"))
                break;
            System.out.println("____________________________________________________________");
            System.out.println(line);
            System.out.println("____________________________________________________________");

        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }


}
