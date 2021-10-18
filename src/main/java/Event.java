public class Event extends Task {
    protected String at;

    public Event(String newTask, String datetime) {
        super(newTask);
        at = datetime;
    }

    public String getCompletedSymbol() {
        if (Completed) {
            return "[E][X]";
        } else {
            return "[E][ ]";
        }
    }

    public String getTaskInfo() {
        return super.getTaskInfo() + " (at: " + at + ")";
    }

}
