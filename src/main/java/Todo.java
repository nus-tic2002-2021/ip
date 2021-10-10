import java.util.ArrayList;

public class Todo extends Task{

    public Todo(String description){
        super(description);
    }

    public void setDone(){
        this.isDone = true;
        System.out.println("\tNice! I've marked this task as done:\n" +
                            "\t  [T][X] " + description);
    }

    public void print(){
        if (isDone) {
            System.out.println("\t  [T][X] " + description);
        }
        else {
            System.out.println("\t  [T][ ] " + description);
        }
    }
    public String getTask(){
        return "T";
    }

    public String getDescription(){
        return description;
    }
    public String getSave(){
        String s = getTask() + " | " +  getDone() + " | " + getDescription();
        return s;
    }

    @Override
    public String toString() {
        String box = "[T][ ] ";
        if (isDone){
            box = "[T][X] ";
        }
        return (box + description);
    }
}
