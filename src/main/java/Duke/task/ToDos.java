package Duke.task;

public class ToDos extends Task {
    protected String action;

    /**
     * To Do task constructor
     * @param description - Description of To Do task
     */
    public ToDos(String description, String action) {
        super(description);
        this.action = action;
    }

    /**
     * Prints task by its own style
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Encode function for storage within text file
     *
     */
    @Override
    public String encodeTask() {
        return "T" + super.encodeTask() ;
    }
}
