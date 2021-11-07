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
        System.out.println("Nice! I've marked this task as done:\n" +
                            "  [T][X] " + getDescription());
    }

    public void setType(){
        type = "todo";
    }

    public void print(){
        if (isDone) {
            System.out.println("  [T][X] " + getDescription());
        }
        else {
            System.out.println("  [T][ ] " + getDescription());
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
        return (box + getDescription());
    }
}
