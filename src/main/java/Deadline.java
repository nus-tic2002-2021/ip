public class Deadline extends Task {

    protected String by;
    protected String type;

    public Deadline(String description, String by) {
        //super allows us to grab from the super parent class task's description
        super(description);
        this.by = by;
        this.type = "D";
    }

    public void setBy(String by) {
        this.by = by;
    }
    public String getBy() {
        return by;
    }

    @Override
    public String getType() { return this.type; }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}

// edit the todo to be "todo borrow book"
// user call "event project meeting at Mon 2-4pm
//     Now you have 6 tasks in the list.
// probably can do a enum for the days
//     Got it. I've added this task:
//       [E][ ] project meeting (at: Mon 2-4pm)
//     Now you have 7 tasks in the list.