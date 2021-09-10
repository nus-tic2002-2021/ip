import java.util.Arrays;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static int task_count =0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //description.Getter
    public String getDescription() {return description;}

    //isDone.Setter
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    //isDone.Getter
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    public static void echo(String text){
        //terminate the program if user inputs "bye"
        if (text.equalsIgnoreCase("bye")){
            System.out.println("Bye, i will miss you.");
            System.out.println("=========================END===========================");
            System.exit(0);
        } else {
            System.out.println("added: " + text);
            System.out.println("=======================================================");
        }
    }

    public static Task[] addToList (String text, Task[] tasks){
        //print out added: text by calling Echo method
        echo(text);
        tasks[task_count] = new Task(text);
        task_count++;

        //return an array of first n elements
        return Arrays.copyOf(tasks, task_count);
    }

    public static void printList (Task[] tasks){
        //print out every element in the array
        System.out.println("Here are the tasks in your list:");
        for (int i =0; i < tasks.length; i ++){
            if (tasks[i] != null){
                System.out.println(i+1 + "." + "[" + tasks[i].getStatusIcon() +"] " + tasks[i].getDescription());
            }
        }
    }
}
