public class Event extends Task{
    String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
        type = Types.EVENT;
    }

    public void showTask(){
        String mark = isDone ? "X" : " ";
        System.out.println("[E][" + mark + "] " + description + " (at: " + at + ")");
    }
}