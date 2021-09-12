import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> taskList = new ArrayList<>();
    public static void printLine(){
        System.out.println("    -----------------------------------------------");
    }

    public static void dukeGreet(){
        printLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?\n");
        printLine();
    }

    public static void addTask(String req){
        Task newTask;
        String type = req.trim().split(" ")[0];
        String description;
        if(type.equals("todo")){
            description = req.replaceFirst("todo", "").trim();
            newTask = new Todo(description);
        } else if(type.equals("deadline")){
            String[] descriptions = req.replaceFirst("deadline", "").trim().split("/by ");
            description = descriptions[0].trim();
            String by = descriptions[1];
            newTask = new Deadline(description, by);
        } else if(type.equals("event")){
            String[] descriptions = req.replaceFirst("event", "").trim().split("/at ");
            description = descriptions[0].trim();
            String at = descriptions[1];
            newTask = new Event(description, at);
        } else{
            System.out.println("Error: Input is invalid!");
            return;
        }
        taskList.add(newTask);
        System.out.println("     Got it. I've added this task:");
        System.out.print("       ");
        newTask.showTask();
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void dukeEcho(){
        String req;
        Scanner sc = new Scanner(System.in);
        while(!(req = sc.nextLine()).equals("bye")){
            System.out.println(req + "\n");
            printLine();
            if(req.equals("list")){
                System.out.println("     Here are the tasks in your list:");
                for(int i = 0; i < taskList.size(); i++){
                    System.out.print("     " + (i + 1) + ".");
                    taskList.get(i).showTask();
                }
            }
            else if(req.substring(0, 4).equals("done")){
                int idx = Integer.parseInt(req.substring(4).trim()) - 1;
                taskList.get(idx).doTask();
                System.out.println("     Nice! I've marked this task as done:");
                System.out.print("       ");
                taskList.get(idx).showTask();
            }
            else {
                addTask(req);
            }
            System.out.println();
            printLine();
        }
        System.out.println(req + "\n");
        printLine();
        System.out.println("     Bye.Hope to see you again soon!\n");
        printLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        dukeGreet();
        dukeEcho();

    }
}
