package duke.tasklist;

import java.util.ArrayList;


public class Event extends Task{
    public Event(String description){
        super(description);
        this.type = "E";
    }

    @Override
    public String getDescription() {return description.substring(6,description.indexOf('/'));}

    public String getDatetime() {
        return description.substring(description.indexOf('/')+3);
    }

    public static void print(ArrayList<Task> tasks){
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t[" + tasks.get(tasks.size()-1).getType() +"]" + "[" + tasks.get(tasks.size()-1).getStatusIcon() +"] " +""+tasks.get(tasks.size()-1).getDescription() + "(at:" + tasks.get(tasks.size()-1).getDatetime() + ")");
        System.out.println("Now you have " + tasks.size() +" tasks in the list.");
        System.out.println("=======================================================");
    }

    @Override
    public String printList (){
        //print out every element in the array
        return "[" + getType() +"]" + "[" + getStatusIcon() +"] " + getDescription() + "(by:" + getDatetime() + ")";
    }
}
