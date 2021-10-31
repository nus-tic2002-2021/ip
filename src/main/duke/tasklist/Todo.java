package duke.tasklist;

import java.util.ArrayList;

public class Todo extends Task{
    public Todo(String description){
        super(description);
        this.type = "T";
    }

    public String getDescription() {return description.substring(5);}

    public static void print(ArrayList<Task> tasks){
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t[" + tasks.get(tasks.size()-1).getType() +"]" + "[" + tasks.get(tasks.size()-1).getStatusIcon() +"] " + tasks.get(tasks.size()-1).getDescription());
        System.out.println("Now you have " + (tasks.size()) +" tasks in the list.");
        System.out.println("=======================================================");
    }

    public String printList (){
            return "[" + getType() +"]" + "[" + getStatusIcon() +"] " + getDescription();
    }

}
