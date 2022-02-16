public class Event extends Task {

    protected String at;
    protected String time;
    protected String type;

    public Event(String description, String at, String time) {
        super(description);
        this.at = at;
        this.time = time;
        this.type = "E";
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String getType() { return this.type; }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + " " + time + ")";
    }
}
