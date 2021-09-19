public class Deadline extends Task{
    public Deadline(String description){
        super(description);
        this.type = "D";
    }

    @Override
    public String getDescription() {return description.substring(9,description.indexOf('/'));}

    public  String getDatetime(){
        return description.substring(description.indexOf('/')+3);
    }

    public static void print(Task[] tasks){
        System.out.println("Got it. I've added this task: ");
        System.out.println("    [" + tasks[task_count].getType() +"]" + "[" + tasks[task_count].getStatusIcon() +"] " + tasks[task_count].getDescription() + "(by:" + tasks[task_count].getDatetime() + ")");
        System.out.println("Now you have " + (task_count+1) +" tasks in the list.");
        System.out.println("=======================================================");
    }

    public String printList (){
        //print out every element in the array
        return "[" + getType() +"]" + "[" + getStatusIcon() +"] " + getDescription() + "(by:" + getDatetime() + ")";
    }

}
