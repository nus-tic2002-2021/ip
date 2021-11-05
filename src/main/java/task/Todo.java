package task;

public class Todo extends Task{

    public Todo(String description){
        super(description);
        setType();
    }

    public Todo(String description, Boolean isDone) {
        super(description);
        this.isDone = isDone;
        setType();
    }

    public void setDone(){
        this.isDone = true;
        System.out.println("\tNice! I've marked this task as done:\n" +
                            "\t  [T][X] " + getDescription());
    }

    public void print(){
        if (isDone) {
            System.out.println("\t  [T][X] " + getDescription());
        }
        else {
            System.out.println("\t  [T][ ] " + getDescription());
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
    public void setType(){
        type = "todo";
    }
    @Override
    public String toString() {
        String box = "[T][ ] ";
        if (isDone){
            box = "[T][X] ";
        }
        return (box + getDescription());
    }
}
