package task;

public class Event extends Task {

    public Event(String description, String ends) {
        super(description);
        super.type("E");
        super.timing(ends);
        super.isDone = false;
    }

    @Override
    public String printTask() {
        return super.printTask() + " (at: " + super.timing + ")";
    }
}
