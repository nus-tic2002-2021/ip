package Duke.DukeLogic;

import Duke.Models.Task;

/**
 * Ui class holds Ui display methods that interact with the user.
 */
public class Ui {
    public final static String LINE = "____________________________________________________________";

    /**
     * Prints the opening message of Duke.
     */
    public static void startDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE + "\nWelcome to\n" + logo + " chatbot!");
    }

    /**
     * Prints a greeting from Duke.
     */
    public static void greet() {
        String start = "Hello, I'm Duke.\n"
                + "What can I do for you?\n";
        System.out.println(LINE + "\n" + start + LINE + "\n");
    }

    /**
     * Prints a message with the number of task inside the task list of Duke.
     */
    public static void printTaskCount() {
        System.out.println("Now you have " + TaskList.size() + " tasks in the list.");
    }

    /**
     * Prints a message that a Task that has been added.
     * @param newTask
     */
    public static void printTaskAdded(Task newTask) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:\n"
                + newTask.getTaskInfo());
    }

    public static void requestPriorityLevel() {
        System.out.println("What is the priority level of this task?" +
                "\n(enter 1 - LOW priority, 2 - MEDIUM priority, 3 - HIGH priority." +
                "\nOther inputs will assume task has NIL priority level.)");
        System.out.println(LINE);
    }
}
