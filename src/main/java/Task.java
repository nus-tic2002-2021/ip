import java.util.Arrays;

public class Task {
    protected String description;
    protected boolean isDone;
    public String breakLine = "____________________________________________________________\n";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setIsDone(boolean done){
        this.isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone(){
        setIsDone(true);
//        System.out.println(breakLine+"Nice! I've marked this task as done: \n" +
//                " ["+getStatusIcon()+"] " + getDescription() + "\n"+breakLine);
    }

}
