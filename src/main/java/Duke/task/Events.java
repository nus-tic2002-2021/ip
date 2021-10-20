package Duke.task;

public class Events extends Task {
    protected String at;

    /**
     * Event task constructor
     * @param description - Description of Event
     * @param at - Date/Time event is held
     */
    public Events(String description, String at) {
        super(description);
        this.at = at;
    }
    /**
     * Prints task by its own style
     *
     *
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
    /**
     * Encode function for storage within text file
     *
     */
    @Override
    public String encodeTask() {
        return "E" + super.encodeTask() + "|" + at ;
    }
}
