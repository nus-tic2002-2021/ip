package Duke.Ui;

import Duke.Models.Task;
import Duke.TaskList;

public class Ui {
    public static String line = "____________________________________________________________";

    public static void StartDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + "\nWelcome to\n" + logo + " chatbot!");
    }

    public static void Greet() {
        String start = "Hello, I'm Duke.Duke\n"
                + "What can I do for you?\n";
        System.out.println(line + "\n" + start + line + "\n");
    }

    public static void PrintTaskCount() {
        System.out.println("Now you have " + TaskList.size() + " tasks in the list.");
    }

    public static void PrintTaskAdded(Task newTask) {
        System.out.println("Got it. I've added this task:\n"
                + newTask.getTaskInfo());
    }
}
