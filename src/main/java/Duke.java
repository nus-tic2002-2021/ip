import java.util.*;

public class Duke {
    public static void main(String[] args) {
        boolean exit = false;
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("__________________________________________");
        System.out.println("Hello, I'm Duke!" );
        System.out.println("What can I do for you?");
        System.out.println("__________________________________________");

        while (!exit){
            String inputStr = sc.nextLine();
            System.out.println("__________________________________________");

            String words[] = inputStr.split(" ", 2);
            String keyword = words[0].toLowerCase();

            switch(keyword)
            {
                case "list":
                {
                    if(tasks.size() == 0){
                        System.out.println("There's no task now :D");
                    }
                    else {
                        System.out.println("Here are the task(s) in your list:");
                        for (Task task : tasks) {
                            System.out.println(task.getTaskId() + ". " + task.toString());
                        }
                    }
                    break;
                }
                case "done":
                {
                    int doneItemId = Integer.parseInt(words[1]);

                    Task doneTask = tasks.get(doneItemId - 1);
                    doneTask.markAsDone();

                    System.out.println("Amazing! The task is marked as done now:");
                    System.out.println(doneTask.toString());
                    break;
                }
                case "todo":
                case "deadline":
                case "event":
                {
                    Task task = null;
                    boolean isAdded = false;

                    switch (keyword) {
                        case "todo": {
                            task = new ToDo(words[1]);
                            tasks.add(task);
                            isAdded = true;
                            break;
                        }
                        case "deadline": {
                            if (words[1].contains(" /by ")) {
                                String deadlineInfo[] = words[1].split(" /by ");
                                task = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                                tasks.add(task);
                                isAdded = true;
                            }
                            else {
                                System.out.println("Task cannot be added: ");
                                System.out.println("Deadline is not specified.");
                            }
                            break;
                        }
                        case "event": {
                            if (words[1].contains(" /at ")) {
                                String eventInfo[] = words[1].split(" /at ");
                                task = new Event(eventInfo[0], eventInfo[1]);
                                tasks.add(task);
                                isAdded = true;
                            }
                            else {
                                System.out.println("Task cannot be added: ");
                                System.out.println("Event time is not specified.");
                            }
                            break;
                        }
                    }

                    if (isAdded) {
                        System.out.println("Got it! I've added this task: ");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + tasks.size() + " task(s) in the list");
                    }
                    break;
                }
                case "bye":
                {
                    System.out.println("Bye. Hope to see you again soon!");
                    exit = true;
                    break;
                }
                default:
                {
                    System.out.println("Sorry, no command <" + keyword + "> found :(");
                    break;
                }
            }

            System.out.println("__________________________________________");
        }
    }
}
