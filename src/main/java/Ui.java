public class Ui {
    static String line = "____________________________________________________________\n";

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
        System.out.println(line + start + line);
    }

    public static void PrintTaskCount() {
        System.out.println("Now you have " + TaskList.DukeList.size() + " tasks in the list.");
    }

    public static void PrintTaskAdded(Task newTask) {
        System.out.println(line + "\nGot it. I've added this task:\n"
                + newTask.getTaskInfo());
    }
}
