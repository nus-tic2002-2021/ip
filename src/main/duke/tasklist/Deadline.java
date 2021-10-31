package duke.tasklist;

import duke.exception.DukeException;
import duke.tasklist.Task;

import java.util.ArrayList;

public class Deadline extends Task {
    public Deadline(String description){
        super(description);
        this.type = "D";
    }

    @Override
    public String getDescription() {return description.substring(9,description.indexOf('/'));}

    public  String getDatetime() throws DukeException {
        return description.substring(description.indexOf('/')+3);
    }

    public static void print(ArrayList<Task> tasks){
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t[" + tasks.get(tasks.size()-1).getType() +"]" + "[" + tasks.get(tasks.size()-1).getStatusIcon() +"] " + tasks.get(tasks.size()-1).getDescription() + "(by:" + tasks.get(tasks.size()-1).getDatetime() + ")");
        System.out.println("Now you have " + (tasks.size()) +" tasks in the list.");
        System.out.println("=======================================================");
    }

    public String printList (){
        //print out every element in the array
        return "[" + getType() +"]" + "[" + getStatusIcon() +"] " + getDescription() + "(by:" + getDatetime() + ")";
    }

}
