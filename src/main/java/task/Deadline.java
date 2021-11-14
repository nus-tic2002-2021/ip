package task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        super.type("D");
        super.timing(by);
        super.isDone = false;
    }

    @Override
    public String printTask() {
        return super.printTask() + " (by: " + super.timing + ")";
    }
}
