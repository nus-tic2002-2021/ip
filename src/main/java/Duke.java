import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void response(String sentence){
        System.out.println("____________________________________________________________");
        switch (sentence) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "hi":
                System.out.println("Hello");
                break;
            default:
                System.out.println(sentence);
                break;
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String line;
        System.out.println("Hello! I'm Duke\nWhat can I do for you today?");
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        response(line);
    }
}
