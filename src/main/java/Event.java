public class Event extends Todo {
    String at;

    public Event(String description, String start, Integer id) {
        super(description, id);
        at = start;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String start) {
        at = start;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return id + ". [E][" + status + "] " + super.getDescription() + " (at: " + at + ")";
    }
}