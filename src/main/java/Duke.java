
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String logo = " ____        _        \n"
                   + "|  _ \\ _   _| | _____ \n"
                   + "| | | | | | | |/ / _ \\\n"
                   + "| |_| | |_| |   <  __/\n"
                   + "|____/ \\__,_|_|\\_\\___|";
        System.out.println("____________________________________________________");
        System.out.println("Hello! I'm Duke By Justin\nWhat can I do for you?\n" + logo);
        System.out.println("____________________________________________________");

        ArrayList<Task> listOfTasks = new ArrayList<Task>();
        boolean bye = false;

        while (!bye) {

            System.out.print("\ncmd: ");
            String cmd = input.nextLine();

            if (cmd.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________");
                bye = true;
            }

            else if (cmd.equalsIgnoreCase("list")) {

                if (listOfTasks.size() == 0) {
                    System.out.println("____________________________________________________");
                    System.out.println("You have no task! :)");
                    System.out.println("____________________________________________________");
                }

                System.out.println("____________________________________________________");
                for (Task task : listOfTasks) {
                    System.out.println(task.listTask());
                }
                System.out.println("____________________________________________________");

            }

            else if (cmd.startsWith("done")) {
                for (Task task : listOfTasks) {
                    int id = Integer.parseInt(String.valueOf(cmd.charAt(5)));
                    if (task.getID() == id) {
                        task.setDone();
                        System.out.println("____________________________________________________");
                        System.out.println("Nice! I've marked this task as done.\n" + task.getTask());
                        System.out.println("____________________________________________________");
                    }
                }
            }

            else if (cmd.startsWith("blah")) {
                System.out.println("____________________________________________________");
                System.out.println("Oops! Sorry, I don't know what you mean ☹");
                System.out.println("____________________________________________________");
            }

            else {

                Task newTask = new Task(cmd);
                listOfTasks.add(newTask);

                if (cmd.startsWith("todo")) {
                    newTask.setToDo();
                }

                else if (cmd.startsWith("deadline")) {
                    newTask.setDeadline();
                }

                System.out.println("____________________________________________________");
                System.out.println("Got it! I've added this task.\n" + newTask.getTask());
                System.out.println("Now you have " + newTask.getTotalCount() + " in the list.");
                System.out.println("____________________________________________________");

            }

        }
    }
}
