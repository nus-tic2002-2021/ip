package duke.task;

public class Deadline extends Task{
    String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
        type = Types.DEADLINE;
    }

    public Deadline(boolean isDone, String description, String by){
        super(description);
        this.by = by;
        this.isDone = isDone;
        type = Types.DEADLINE;
    }

    public void showTask(){
        String mark = isDone ? "X" : " ";
        System.out.println("[D][" + mark + "] " + description + " (by: " + by + ")");
    }

    public String toStoreString(){
        int state = isDone ? 1 : 0;
        return 'D' + " | " + state + " | " + description + " | " + by + System.lineSeparator();
    }

}
