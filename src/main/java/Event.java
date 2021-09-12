public class Event extends Task{
    String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
        taskType = 'E';
    }

    public void showTask(){
        String mark = isDone ? "X" : " ";
        System.out.println("[" + taskType + "][" + mark + "] " + description + " (at: " + at + ")");
    }
}