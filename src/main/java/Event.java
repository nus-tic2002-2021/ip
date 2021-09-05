public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "Got it. I've added this task: \n  "+ super.toString() + " (at: " + at + ")";
    }
}
