import java.util.Locale;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String line;
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            line = in.nextLine();
            if (line.toLowerCase(Locale.forLanguageTag(line)).equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else {
                System.out.println(line);
                
            }
        }
    }

}