public class Todo extends Task{
    public Todo(String description){
        super(description);
        super.type = "T";
    }

    public String getDescription() {return description.substring(5);}

    public static void print(Task[] tasks){
        System.out.println("Got it. I've added this task: ");
        System.out.println("    [" + tasks[task_count].getType() +"]" + "[" + tasks[task_count].getStatusIcon() +"] " + tasks[task_count].getDescription());
        System.out.println("Now you have " + (task_count+1) +" tasks in the list.");
        System.out.println("=======================================================");
    }
}
