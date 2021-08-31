import java.util.Scanner;

public class Duke {

    static Scanner in = new Scanner(System.in);

    public static void StartDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to\n" + logo + " chatbot! \n");
    }

    public static void Greet() {
        String start = "Hello, I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(start);
    }

    public static void Echo() {
        Boolean stop = false;
        while (stop == false) {
            String word = in.next();
            if (word.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                stop = true;
            } else {
                System.out.println(word);
            }
        }
    }

    public static void main(String[] args) {
        StartDuke();
        Greet();
        Echo();
    }
}
