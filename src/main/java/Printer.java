
import java.util.ArrayList;

public class Printer {

    protected final static String LINE = "_____________________________________________________________\n";
    protected final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    protected final static String INTRO = "Hello! I'm Duke By Justin ☺\nWhat can I do for you?\n" + LOGO;
    protected final static String INVALID_CMD = "Oops! Sorry, I don't know what you mean ☹\n";
    protected final static String BYE = "Bye. Hope to see you again soon!\n";
    protected final static String NO_TASK = "You have no task in your list!\n";
    protected final static String INVALID_DONE = "Oops! Please specify the task id to mark it as done ☹\n";
    protected final static String INVALID_TODO = "Oops! Please provide the description of your todo task ☹\n";
    protected final static String INVALID_EVENT = "Oops! Please provide the description of your event task ☹\n";
    protected final static String INVALID_DEADLINE = "Oops! Please provide the description of your deadline task ☹\n";

    public Printer() {
        System.out.print(LINE+INTRO+LINE);
    }

    public static void printIntro() {
        System.out.print(LINE+INTRO+LINE);
    }

    public static void printHeader() {
        System.out.print("\ncmd: ");
    }

    public static void printInvalidCmd() {
        System.out.print(LINE+INVALID_CMD+LINE);
    }

    public static void printBye() {
        System.out.print(LINE+BYE+LINE);
    }

    public static void printNoTask() {
        System.out.print(LINE+NO_TASK+LINE);
    }

    public static void printList() {
        System.out.print(LINE + "Here are the tasks in your list:\n");
        for (Task task : Parser.taskList) System.out.println(task.toString());
        System.out.print(LINE);
    }

    public static void printDone(Task task) {
        System.out.print(LINE + "Nice! I've marked this task as done.\n" + task.toString() + "\n" + LINE);
    }

    public static void printInvalidDone() {
        System.out.print(LINE+INVALID_DONE+LINE);
    }

    public static void printInvalidTodo() {
        System.out.print(LINE+INVALID_TODO+LINE);
    }

    public static void printInvalidEvent() {
        System.out.print(LINE+INVALID_EVENT+LINE);
    }

    public static void printInvalidDeadline() {
        System.out.print(LINE+INVALID_DEADLINE+LINE);
    }

    public static void printTask(ArrayList<Task> taskList) {
        System.out.print(LINE + "Got it! I've added this task.\n" + taskList.get(taskList.size() - 1).toString());
        System.out.print("\nNow you have " + taskList.get(taskList.size() - 1).getTotalCount() + " in the list.\n" + LINE);
    }

}
