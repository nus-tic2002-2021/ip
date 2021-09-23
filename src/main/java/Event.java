public class Event extends Task {

    protected String at;

    public Event(String description, String ends) {
        super(description);
        this.at = ends;
    }

    @Override
    public String printTask() {
        return "[E]" + super.printTask() + " (at: " + at + ")";
    }
}
