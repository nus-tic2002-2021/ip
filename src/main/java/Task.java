import java.util.Arrays;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static int task_count =0;
    protected String type;
    protected String datetime;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //description.Getter
    public  String getDescription() {return description;}

    //isDone.Setter
    public void setDone() {isDone = true;}

    //isDone.Getter
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType(){return type;}

    public  String getDatetime(){
        return datetime;
    }

    public static Task[] addToList (String text, Task[] tasks){
        if (text.contains("todo")) {
            tasks[task_count] = new Todo(text);
            Todo.print(tasks);
        } else if (text.contains("event")) {
            tasks[task_count] = new Event(text);
            Event.print(tasks);
        } else if (text.contains("deadline")) {
            tasks[task_count] = new Deadline(text);
            Deadline.print(tasks);
        } else {
            System.out.println("Sorry! I can't recognise the command, please reenter!");
            System.out.println("=======================================================");
        }
        task_count++;

        //return an array of first n elements
        return Arrays.copyOf(tasks, task_count);
    }

    public String printList (){return "";}

}