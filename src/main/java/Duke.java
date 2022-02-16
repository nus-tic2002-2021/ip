import java.lang.reflect.Array;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Duke {

    public static ArrayList<Task> storedTask = new ArrayList<Task>();
    public static GUI gui = new GUI();

    public static void getList(){
        String line;
        String response = "";

        if( storedTask.size() < 1){
            gui.botResponse("Todo list is empty. Try adding something by typing todo.");
            return;
        }
        for (int i=1; i<=storedTask.size(); i++){
            String id = String.valueOf(i);
            String task;
            if(i!=storedTask.size()){
                task = id + ": " + "[" + storedTask.get(i-1).getType() + "]" + storedTask.get(i-1).toString() + "\n";
            } else {
                task = id + ": " + "[" + storedTask.get(i-1).getType() + "]" + storedTask.get(i-1).toString();
            }
            response += task;
        }
        gui.botResponse( "Here is your list of tasks." + "\n" + response);
    }

    public static void delete(int index){
        ArrayList<Task> temp = new ArrayList<Task>();

        if( storedTask.size() < 1){
            gui.botResponse("nothing to delete");
        } else if( index > storedTask.size()){
            gui.botResponse("Hmm I cant find that. Please try again.");
        } else {
            for (int i = 0; i < storedTask.size(); i++) {
                if (i != index) {
                    temp.add(storedTask.get(i));
                }
            }
            storedTask = temp;
            gui.botResponse("Deleted");
        }
    }

    public static void find(String searchFor){
        ArrayList<Task> results = new ArrayList<Task>();
        for(int i=0; i<storedTask.size(); i++){
            Pattern pattern = Pattern.compile(searchFor, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(storedTask.get(i).getDescription());
            if(matcher.find()){
                results.add(storedTask.get(i));
            }
        }
        if( results.size() < 1){
            gui.botResponse("No matching tasks found.");
        } else{
            gui.botResponse("We have found the following...");
            for (int i=1; i<=results.size(); i++) {
                String type = results.get(i-1).getType();
                String desc = results.get(i-1).toString();
                String id = String.valueOf(i);
                String response = id + ": " + "[" + type + "]" + desc + "\n";
                gui.botResponse(response);
            }
        }
    }

    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    public static void updateTaskStatus(String line){
        boolean matched = false;
        for (int i=0; i<storedTask.size(); i++) {
            if (storedTask.get(i).getDescription().equals(line)) {
                storedTask.get(i).markDone();
                gui.botResponse("Great! I've marked this task as done" + "\n"
                        + "[" + storedTask.get(i).getStatusIcon() +  "] " + storedTask.get(i).getDescription());
                matched = true;
                break;
            }
        }
        if (!matched) {
            gui.botResponse("Cannot find " + '"' + line + '"' + ".");
        }
    }

    public static void GeneralResponse(String sentence){
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
                gui.botResponse("Bye. Your session has been saved. Hope to see you again soon!");
                break;
            case "hi":
                gui.botResponse("Hello");
                break;
            default:
                break;
        }
    }

    public static String getCommand(String line){
        int count = 0;
        String[] command = new String[10];
        String[] line2 = line.toLowerCase().split(" ");
        String[] keywords = {"todo", "deadline", "event", "done", "list", "delete", "find"};
        for(String i : line2 ){
            if(Arrays.asList(keywords).contains(i)){
                command[count] = i;
                count++;
            }
        }
        if(count > 1){
            gui.botResponse("whoa! One at a time :) Did you mean to run " + "'" + command[0] + "'" + "?" );
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

    public static void massOpsDelete(int start_index, int end_index, String line){
        ArrayList<Task> temp = new ArrayList<Task>();
        ArrayList<Integer> range = new ArrayList<Integer>();
        if (start_index >= 0 && end_index >= 0){
            for (int i = start_index; i<=end_index; i++){
                System.out.println("massops: " + i);
                range.add(i);
            }
            for (int j = 0; j<storedTask.size(); j++){
                if( storedTask.size() < 1){
                    gui.botResponse("nothing to delete");
                    break;
                } else if( j > storedTask.size()){
                    gui.botResponse("Hmm I cant find that. Please try again.");
                    break;
                } else {
                    if (!range.contains(j)) {
                        temp.add(storedTask.get(j));
                    }
                }
            }
            storedTask = temp;
            gui.botResponse("Mass deleted");
        } else if (!line.equals("")){
            for(int i=0; i<storedTask.size(); i++){
                Pattern pattern = Pattern.compile(line, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(storedTask.get(i).getDescription());
                if(matcher.find()){
                    delete(i);
                }
            }
        }
    }

    public static void performCommand(String line, String command){
        if (command.equals("todo")) {
            line = line.toLowerCase();
            int commandIndex = line.indexOf("todo");
            String description = line.substring(commandIndex + 4);
            Task record = new Task(description.strip());
            if(description.strip().equals("")){
                gui.botResponse("I think you want to add a todo, input it like this... \n [ todo xxx ]");
            } else {
                storedTask.add(record);
                String response = "-  [ T ] " + "[ ] " + description.strip();
                gui.botResponse("Sure! I have added that to the list." + "\n" + response);
                gui.botResponse("Now you have " + storedTask.size() + " tasks in your list.");
            }
        }
        if (command.equals("done")){
            String taskName = line.replace("done", "").strip();
            taskName = taskName.replace("completed", "").strip();
            updateTaskStatus(taskName);
        }
        if (command.equals("deadline")){
            line = line.toLowerCase();
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
                    gui.botResponse("Please indicate description and deadline");
                } else {
                    Task deadline = new Deadline(description.strip(), byDate.strip());
                    storedTask.add(deadline);
                    String response = "-  [ D ] " + "[ ] " + description.strip() + " ( by: " + byDate.strip() + " ) ";
                    gui.botResponse("Sure! I have added that to the list." + "\n" + response);
                    gui.botResponse("Now you have " + storedTask.size() + " tasks in your list.");
                }
            } else {
                gui.botResponse("To add a new deadline, you should input... \n [ deadline xxx by monday ]");
            }
        }
        if (command.equals("event")){
            line = line.toLowerCase();
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
                    gui.botResponse("Please indicate description and event timeline");
                } else {
                    Task event = new Event(description.strip(), at.strip().split(" ")[0], time);
                    storedTask.add(event);
                    String response = "-  [ E ] " + "[ ] " + description.strip() + " ( at: " + at.strip() + " ) ";
                    gui.botResponse("Sure! I have added that to the list." + "\n" + response);
                    gui.botResponse("Now you have " + storedTask.size() + " tasks in your list.");
                }
            } else {
                gui.botResponse("To add a new event, you have to say... \n [ event xxx at monday 3pm-4pm ]");
            }
        }
        if(command.equals("list")){
            getList();
        }
        if(command.equals("delete")){
            String word = line.split(" ")[1].strip();

            if(word.equalsIgnoreCase("all")){
                int start_index = -1;
                int temp = -1;
                int end_index = -2;
                for(String i : line.split(" ")) {
                    if(isNumeric(i)) {
                        temp = Integer.parseInt(i);
                        if(start_index < 0){
                            if(temp <= storedTask.size()){
                                start_index = temp;
                                end_index = temp;
                            }
                        }
                        if(temp > end_index){
                            end_index = temp;
                        }
                    }
                }
                System.out.println("start:" + start_index);
                System.out.println("end:" + end_index);
                if(start_index < end_index){
                    if(end_index <= storedTask.size()) {
                        massOpsDelete(start_index - 1, end_index - 1, "");
                    } else {
                        gui.botResponse("End index too high.");
                    }
                } else if (start_index == end_index) {
                    gui.botResponse("start index and end index should not be the same!");
                } else {
                    gui.botResponse("Looks like the start index is larger than the end index!");
                }
            } else if (!isNumeric(word)){
                gui.botResponse("My developer is being lazy! I'll tell him to teach me how to delete by description. Promise!");
            } else {
                int index = Integer.parseInt(word);
                delete(index - 1);
            }
        }
        if(command.equals("find")){
            int commandIndex = line.indexOf("find");
            String searchParams = line.substring(commandIndex+5);
            find(searchParams);
        }
        GeneralResponse(line);
    }

    public static void main(String[] args) {
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
        gui.botResponse("Hello! I'm Duke. What can I do for you today?");
    }
}
