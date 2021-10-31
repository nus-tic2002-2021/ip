import java.util.ArrayList;

public class Todo extends Task{
    public Todo(String description){
        super(description);
        this.type = "T";
    }

    public String getDescription() {return description.substring(5);}

    public static void print(ArrayList<Task> tasks){
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t[" + tasks.get(task_count).getType() +"]" + "[" + tasks.get(task_count).getStatusIcon() +"] " + tasks.get(task_count).getDescription());
        System.out.println("Now you have " + (task_count+1) +" tasks in the list.");
        System.out.println("=======================================================");
    }

    public String printList (){
        return "[" + getType() +"]" + "[" + getStatusIcon() +"] " + getDescription();
    }

}
