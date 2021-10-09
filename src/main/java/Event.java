public class Event extends Task {
    protected String at;

    public Event(String newTask, String datetime) {
        super(newTask);
        at = datetime;
    }
    
}
