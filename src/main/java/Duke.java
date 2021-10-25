import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static ArrayList<Task> storedTask = new ArrayList<Task>();

    public static void getList(){
        if( storedTask.size() < 1){
            System.out.println("Todo list is empty. Try adding something by typing todo.");
        }
        for (int i=1; i<storedTask.size()+1; i++){
            System.out.format("%d: " + "[" + storedTask.get(i-1).getStatusIcon() +  "] " +
                    storedTask.get(i-1).getDescription() + "\n" , i);
        }
    }

    public static void updateTaskStatus(String line){
        boolean matched = false;
        for (int i=0; i<storedTask.size(); i++) {
            if (storedTask.get(i).getDescription().equals(line)) {
                storedTask.get(i).markDone();
                matched = true;
                break;
            }
        }
        if (!matched){
            System.out.println("Cannot find " + '"' + line + '"' + ".");
        }
    }

    public static void response(String sentence){
        System.out.println("____________________________________________________________");
        switch (sentence) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "hi":
                System.out.println("Hello");
                break;
            case "to_do":
                break;
            case "list":
                getList();
                break;
            default:
                System.out.println(sentence);
                break;
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String line;
        String todo;
        System.out.println("Hello! I'm Duke\nWhat can I do for you today?");
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            Inspect userText = new Inspect(line);
            // Closing app takes precedence #1
            if(userText.bye()) {
              line = "bye";
            }
            // Add task 2nd priority
            if (userText.addTask()) {
                System.out.println("Sure, what would you like add");
                Scanner scanner = new Scanner(System.in);
                todo = scanner.nextLine();
                Task record = new Task(todo);
                storedTask.add(record);
                System.out.println("added");
            }
            // Check if user says completed
            if (userText.doneTask()){
                String taskName = line.replace("done", "").strip();
                taskName = taskName.replace("completed", "").strip();
                updateTaskStatus(taskName);
            }

            response(line);
        } while (!line.equals("bye"));
    }
}
