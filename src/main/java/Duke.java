import java.util.Locale;
import java.util.Scanner;


public class Duke {
    private final static String GOOD_BYE = "bye";

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.toLowerCase(Locale.ROOT).equals(GOOD_BYE)) {
            System.out.println(input);
            input = in.nextLine();
        }
        System.out.println(GOOD_BYE);
    }
}
