import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
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
            System.out.format("%d: " + "[" + storedTask.get(i-1).getType() + "]" + storedTask.get(i-1).toString() + "\n" , i);
        }
    }

    public static void updateTaskStatus(String line){
        boolean matched = false;
        for (int i=0; i<storedTask.size(); i++) {
            if (storedTask.get(i).getDescription().equals(line)) {
                storedTask.get(i).markDone();
                System.out.println("Great! I've marked this task as done" + "\n"
                        + "[" + storedTask.get(i).getStatusIcon() +  "] " + storedTask.get(i).getDescription());
                matched = true;
                break;
            }
        }
        if (!matched) {
            System.out.println("Cannot find " + '"' + line + '"' + ".");
        }
    }

    public static void response(String sentence){
        switch (sentence) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "hi":
                System.out.println("Hello");
                break;
            default:
                break;
        }
    }

    public static String getCommand(String line){
        int count = 0;
        String[] command = new String[10];
        String[] line2 = line.split(" ");
        String[] keywords = {"todo", "deadline", "event", "done", "list"};
        for(String i : line2 ){
            if(Arrays.asList(keywords).contains(i)){
                command[count] = i;
                count++;
            }
        }
        if(count > 1){
            System.out.println("whoa! One at a time :) Did you mean to run " + "'" + command[0] + "'" + "?" );
            return "unknown";
        }else if (count == 1){
            return command[0];
        } else {
            return "no command";
        }
    }

    public static void main(String[] args) {
        String line;
        String todo;
        String command;
        System.out.println("Hello! I'm Duke\nWhat can I do for you today?");
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            command = getCommand(line);
            System.out.println("the command is: " + command);
            Inspect userText = new Inspect(line);
            if(userText.bye()) {
              line = "bye";
            }
            if (command.equals("todo")) {
                System.out.println("Sure, what would you like add");
                Scanner scanner = new Scanner(System.in);
                todo = scanner.nextLine();
                Task record = new Task(todo);
                storedTask.add(record);
                System.out.println("added");
            }
            if (command.equals("done")){
                String taskName = line.replace("done", "").strip();
                taskName = taskName.replace("completed", "").strip();
                updateTaskStatus(taskName);
            }
            if (command.equals("deadline")){
                int byIndex = line.lastIndexOf("by");
                int commandIndex = line.indexOf("deadline");
                int flag = 0;
                if((byIndex < 0 || commandIndex < 0) || (byIndex < commandIndex)){
                    flag = 1;
                }
                if(flag == 0) {
                    String byDate = line.substring(byIndex + 2);
                    String description = line.substring(commandIndex + 8, byIndex);
                    if (byDate.strip().length() == 0 || description.strip().length() == 0) {
                        System.out.println("Please indicate description and deadline");
                        } else {
                            Task deadline = new Deadline(description.strip(), byDate.strip());
                            storedTask.add(deadline);
                            System.out.println("added");
                        }
                } else {
                    System.out.println("Whoops, I didn't quite get that. If you meant to add a new deadline, please follow this format: \n" +
                            "deadline 'your description' by 'day'");
                }
            }
            if (command.equals("event")){
                int atIndex = line.lastIndexOf("at");
                int commandIndex = line.indexOf("event");
                int flag = 0;
                if((atIndex < 0 || commandIndex < 0) || (atIndex < commandIndex)){
                    flag = 1;
                }
                if(flag == 0) {
                    String at = line.substring(atIndex + 2);
                    String description = line.substring(commandIndex + 5, atIndex);
                    // current time only takes next word after day. will need to fine tune for more regex.
                    String time = at.strip().split(" ").length > 1 ? at.strip().split(" ")[1] : "";
                    if (at.strip().length() == 0 || description.strip().length() == 0) {
                        System.out.println("Please indicate description and event timeline");
                    } else {
                        Task event = new Event(description.strip(), at.strip().split(" ")[0], time);
                        storedTask.add(event);
                        System.out.println("added");
                    }
                } else {
                    System.out.println("Whoops, I didn't quite get that. If you  meant to add a new event, please follow this format: \n" +
                            "event 'your description' by 'day'");
                }
            }
            if(command.equals("list")){
                getList();
            }

            response(line);
        } while (!line.equals("bye"));
    }
}
