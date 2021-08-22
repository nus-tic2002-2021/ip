import java.util.Locale;
import java.util.Scanner;


public class Duke {
    private final static String END_PROGRAM = "bye";
    private final static String END_STATEMENT = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.toLowerCase(Locale.ROOT).equals(END_PROGRAM)) {
            System.out.println(input);
            input = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
