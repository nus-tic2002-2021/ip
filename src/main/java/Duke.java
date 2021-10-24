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
            String[] input = sc.nextLine().split(" ", 2);
            //System.out.println("\t" + horizontalLine);
            if (input[0].contains("bye")) {
                System.out.println("Bye. Hope to see you again soon!"+ "\n"+ logo);
                break;
            } else if (input[0].contains("list")) {
                System.out.println("\tHere are the tasks in your list:");
                int taskID = 1;
                while (list[taskID - 1] != null) {
                    System.out.println("\t" + taskID + ". " + list[taskID - 1]);
                    taskID++;
                }
            } else if (input.equals("done")) {
                System.out.println(input[1]);
                Task doneTask = list[Integer.parseInt(input[1]) - 1];
                doneTask.setDone();
                System.out.println("\tNice! I've marked this task as done: ");
                System.out.println("\t\t" + list[Integer.parseInt(input[1]) - 1]);

            } else {
                System.out.println("\tGot it. I've added this task: ");
                Task newTask;
                if (input[0].equals("todo")) {
                    newTask = new Todo(input[1]);
                } else if (input[0].equals("deadline")) {
                    String[] task = input[1].split("/");
                    newTask = new Deadline(task[0], task[1]);
                } else {
                    String[] task = input[1].split("/");
                    newTask = new Event(task[0], task[1]);
                }
                list[currentTask] = newTask;
                currentTask++;
                System.out.println("\t\t" + newTask);
                System.out.println(String.format("\tNow you have %d tasks in the list.", Task.getTotalTasks()));
            }
            System.out.println("\t");

        }
    }
}