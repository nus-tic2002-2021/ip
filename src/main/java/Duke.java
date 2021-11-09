import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Duke {

    public static ArrayList<Task> storedTask = new ArrayList<Task>();

    public static void getList(){
        if( storedTask.size() < 1){
            System.out.println("Todo list is empty. Try adding something by typing todo.");
        }
        for (int i=1; i<=storedTask.size(); i++){
            System.out.format("%d: " + "[" + storedTask.get(i-1).getType() + "]" + storedTask.get(i-1).toString() + "\n" , i);
        }
    }

    public static void delete(int index){
        ArrayList<Task> temp = new ArrayList<Task>();
        if( storedTask.size() < 1){
            System.out.println("nothing to delete");
        } else {
            for (int i = 0; i < storedTask.size(); i++) {
                if (i != index) {
                    temp.add(storedTask.get(i));
                }
            }
            storedTask = temp;
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
                try{
                    FileWriter writer = new FileWriter("data/duke.txt");
                    BufferedWriter buffer = new BufferedWriter(writer);
                    buffer.write("");
                }catch (IOException e) {
                    e.printStackTrace();
                }
                saveCurrentTasks();
                System.out.println("Bye. Your session has been saved. Hope to see you again soon!");
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
        String[] keywords = {"todo", "deadline", "event", "done", "list", "delete"};
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

    public static void generateTasks(String[] line){
        switch(line[0]){
            case "T":
                Task task = new Task(line[2]);
                if(line[1].equals("1")){
                    task.markDone();
                }
                storedTask.add(task);
                break;
            case "D":
                Task deadline = new Deadline(line[2], line[3]);
                if(line[1].equals("1")){
                    deadline.markDone();
                }
                storedTask.add(deadline);
                break;
            case "E":
                Task event = new Event(line[2], line[3], line[4]);
                if(line[1].equals("1")){
                    event.markDone();
                }
                storedTask.add(event);
                break;
            default: break;
        }
    }

    public static void saveCurrentTasks() {
        try{
            FileWriter writer = new FileWriter("data/duke.txt", true);
            BufferedWriter buffer = new BufferedWriter(writer);
            for (int i = 0; i < storedTask.size(); i++) {
                buffer.write(storedTask.get(i).getType() + "|");
                buffer.write((storedTask.get(i).getStatusIcon().equals("X") ? "1" : "0") + "|");
                buffer.write(storedTask.get(i).getDescription());
                if(storedTask.get(i).getType().equals("D")){
                    Deadline deadline =(Deadline) storedTask.get(i);
                    buffer.write("|" + deadline.getBy());
                }
                if(storedTask.get(i).getType().equals("E")){
                    Event event = (Event) storedTask.get(i);
                    buffer.write("|" + event.getAt() + "|");
                    buffer.write(event.getTime() + "|");
                }
                buffer.newLine();
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String line;
        String todo;
        String command;


        try {
            FileReader reader = new FileReader("data/duke.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line2;
            String[] strarray;
            while ((line2 = bufferedReader.readLine()) != null) {
                strarray = line2.split(Pattern.quote("|"));
                generateTasks(strarray);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

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
            if(command.equals("delete")){
                int index = Integer.parseInt(line.split(" ")[1].strip());
                delete(index-1);
            }

            response(line);
        } while (!line.equals("bye"));
    }
}
