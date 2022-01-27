package dukeMain.tasks;
/**
 * Represents a child of Task. A <code>ToDos</code> object includes
 * Localdate byDate and String byTime.
 * e.g., <code>"this is a ToDos task"</code>
 */
public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }
    public ToDos(boolean done, String description){
        super(description,done);
    }

    /**
     * Overriding the Parent class's toString.
     * adding the "[T]" in front of the description
     *
     * @return String value
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Overriding the Parent class's toString.
     * To format the ToDos tasks into a string format in the preferred separator.
     * Used for Saving purposes.
     *
     * @param separator String
     * @return String value
     */
    @Override
    public String toStringSaveTask(String separator ){
        separator = " " + separator + " ";
        return "T" +separator+ super.toStringSaveTask(separator);
    }
}