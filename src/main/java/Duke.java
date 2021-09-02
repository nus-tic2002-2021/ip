
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;

public class Duke {

    private final static String LINE = "____________________________________________________\n";
    private final static String LOGO = " ____        _        \n"
                          + "|  _ \\ _   _| | _____ \n"
                          + "| | | | | | | |/ / _ \\\n"
                          + "| |_| | |_| |   <  __/\n"
                          + "|____/ \\__,_|_|\\_\\___|\n";
    private final static String INTRO = "Hello! I'm Duke By Justin ☺\nWhat can I do for you?\n" + LOGO;
    private final static String NO_TASK = "You have no task in your list!\n";
    private final static String BLAH = "Oops! Sorry, I don't know what you mean ☹\n";
    private final static String BYE = "Bye. Hope to see you again soon!\n";
    private static ArrayList<Task> listOfTasks = new ArrayList<Task>();
    private static boolean bye = false;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print(LINE+INTRO+LINE);

        while (!bye) {

            System.out.print("\ncmd: ");
            String cmd = input.nextLine();

            if (cmd.equalsIgnoreCase("bye")) {
                System.out.print(LINE+BYE+LINE);
                bye = true;
            }

            else if (cmd.equalsIgnoreCase("list")) {
                if (listOfTasks.size() == 0)
                    System.out.print(LINE+NO_TASK+LINE);
                else {
                    System.out.print(LINE+"Here are the tasks in your list:\n");
                    for (Task task : listOfTasks)
                        System.out.println(task.listTask());
                    System.out.print(LINE);
                }
            }

            else if (cmd.toLowerCase(Locale.ROOT).startsWith("done")) {
                for (Task task : listOfTasks) {
                    int id = Integer.parseInt(String.valueOf(cmd.charAt(5)));
                    if (task.getID() == id) {
                        task.setDone();
                        System.out.print(LINE+"Nice! I've marked this task as done.\n"+task.getTask()+"\n"+LINE);
                    }
                }
            }

            else if (cmd.toLowerCase(Locale.ROOT).startsWith("blah"))
                System.out.print(LINE+BLAH+LINE);

            else {

                Task newTask = new Task(cmd);
                listOfTasks.add(newTask);

                if (cmd.toLowerCase(Locale.ROOT).startsWith("todo"))
                    newTask.setToDo();

                else if (cmd.toLowerCase(Locale.ROOT).startsWith("deadline"))
                    newTask.setDeadline();

                System.out.print(LINE);
                System.out.println("Got it! I've added this task.\n" + newTask.getTask());
                System.out.println("Now you have " + newTask.getTotalCount() + " in the list.");
                System.out.print(LINE);

            }
        }
    }
}
