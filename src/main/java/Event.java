public class Event extends Task {

    protected String at;
    protected String type;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    public void setAt(String at) {
        this.at = at;
    }
    public String getAt() {
        return at;
    }

    @Override
    public String getType() { return this.type; }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}