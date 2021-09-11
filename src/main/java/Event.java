public class Event extends Task{
    protected String datetime;

    public Event(String description){
        super(description);
        super.type = "E";
    }

    @Override
    public String getDescription() {return description.substring(6,description.indexOf('/'));}

    public  String getDatetime(){
        return description.substring(description.indexOf('/')+3);
    }

    public static void print(Task[] tasks){
        System.out.println("Got it. I've added this task: ");
        System.out.println("    [" + tasks[task_count].getType() +"]" + "[" + tasks[task_count].getStatusIcon() +"] " +""+tasks[task_count].getDescription() + "(at:" + tasks[task_count].getDatetime() + ")");
        System.out.println("Now you have " + (task_count+1) +" tasks in the list.");
        System.out.println("=======================================================");
    }
}
