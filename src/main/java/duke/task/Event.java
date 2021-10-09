package duke.task;

public class Event extends Task{
    String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
        type = Types.EVENT;
    }

    public Event(boolean isDone, String description, String at){
        super(description);
        this.at = at;
        this.isDone = isDone;
        type = Types.EVENT;
    }

    public void showTask(){
        String mark = isDone ? "X" : " ";
        System.out.println("[E][" + mark + "] " + description + " (at: " + at + ")");
    }

    public String toStoreString(){
        int state = isDone ? 1 : 0;
        return 'E' + " | " + state + " | " + description + " | " + at + System.lineSeparator();
    }
}
