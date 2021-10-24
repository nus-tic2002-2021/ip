import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke" + "\n"+ logo + "\n" + "How can I help you today?");
        Task[] list = new Task[100];
        int currentTask = 0;

        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            if (input.toLowerCase(Locale.forLanguageTag(input)).contains("bye")) {
                System.out.println("Bye. Hope to see you again soon!"+ "\n"+ logo);
                break;
            } else if (input.toLowerCase(Locale.forLanguageTag(input)).contains("list")) {
                System.out.println("\tHere are the tasks in your list:");
                int taskID = 1;
                while (list[taskID - 1] != null) {
                    System.out.println("\t" + taskID + ". " + list[taskID - 1]);
                    taskID++;
                }
            } else if (input.equals("done")) {
                int task = sc.nextInt();
                Task FinishedTask = list[task - 1];
                FinishedTask.setDone();
                System.out.println("\tNice! I've marked this task as done: ");
                System.out.println("\t" + "  "+list[task - 1]);
            } else {
                System.out.println("\tadded: " + input);
                Task newTask = new Task(input);
                list[currentTask] = newTask;
                currentTask++;
            }
        }
    }
}