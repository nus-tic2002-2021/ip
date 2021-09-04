import task.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        boolean exit = false;
        TaskList taskList = new TaskList();
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
                case "list": {
                    taskList.printTasks();
                    break;
                }
                case "done":
                {
                    int taskId = Integer.parseInt(words[1]);
                    taskList.setDone(taskId);
                    break;
                }
                case "todo":
                case "deadline":
                case "event":
                {
                    Task task = null;

                    switch (keyword) {
                        case "todo": {
                            task = new ToDo(words[1]);
                            taskList.addTask(task);
                            break;
                        }
                        case "deadline": {
                            try {
                                String deadlineInfo[] = words[1].split(" /by ");
                                task = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                                taskList.addTask(task);
                            }
                            catch(Exception e) {
                                System.out.println("Task cannot be added: ");
                                System.out.println("Deadline or description is missing.");
                            }
                            break;
                        }
                        case "event": {
                            try {
                                String eventInfo[] = words[1].split(" /at ");
                                task = new Event(eventInfo[0], eventInfo[1]);
                                taskList.addTask(task);
                            }
                            catch(Exception e) {
                                System.out.println("Task cannot be added: ");
                                System.out.println("Event time or description is missing");
                            }
                            break;
                        }
                    }
                    break;
                }
                case "bye": {
                    System.out.println("Bye. Hope to see you again soon!");
                    exit = true;
                    break;
                }
                default: {
                    System.out.println("Sorry, no command <" + keyword + "> found :(");
                    break;
                }
            }

            System.out.println("__________________________________________");
        }
    }
}
