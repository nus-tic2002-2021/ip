
import java.util.Scanner;

public class Duke {

    protected final static String LINE = "_____________________________________________________________\n";
    protected final static String LOGO = " ____        _        \n"
                          + "|  _ \\ _   _| | _____ \n"
                          + "| | | | | | | |/ / _ \\\n"
                          + "| |_| | |_| |   <  __/\n"
                          + "|____/ \\__,_|_|\\_\\___|\n";
    protected final static String INTRO = "Hello! I'm Duke By Justin ☺\nWhat can I do for you?\n" + LOGO;
    protected final static String INVALID_CMD = "Oops! Sorry, I don't know what you mean ☹\n";
    protected final static String INVALID_DONE = "Oops! Please specify the task id to mark it as done ☹\n";
    protected final static String INVALID_TODO = "Oops! Please provide the description of your todo task ☹\n";
    protected final static String INVALID_EVENT = "Oops! Please provide the description of your event task ☹\n";
    protected final static String INVALID_DEADLINE = "Oops! Please provide the description of your deadline task ☹\n";
    protected static boolean isNotBye = true;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print(LINE+INTRO+LINE);

        while (isNotBye) {
            try {
                System.out.print("\ncmd: ");
                String cmd = input.nextLine();
                isNotBye = Parser.parse(cmd.toLowerCase());
            }
            catch (DukeException e) {
                System.out.print(LINE+INVALID_CMD+LINE);
            }
        }

    }

}
