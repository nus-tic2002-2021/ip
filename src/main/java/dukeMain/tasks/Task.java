package dukeMain.tasks;

/**
 * Represents Task used in a TaskList. A <code>Task</code> object includes
 * String description and boolean done
 * e.g., <code>"This is an Event Task"</code>
 */
public class Task {
    private String description;
    private boolean done;

    public Task(String item) {
        this.description = item;
        this.done = false;
    }
    public Task(String description,boolean done){
        this.description = description;
        this.done = done;
    }

    /**Setter for done variable
     * default true;
     * */
    public void setDone() {
        this.done = true;
    }

    /**Getter for done variable
     *
     * @return done boolean
     * */
    public boolean isDone() {
        return this.done;
    }

    /** Convert variable done to string value for printing purposes.
     *
     * @return X or " " depending on variable done
     * */
    public String getStatusIcon() {
        return (this.done ? "X" : " "); // mark done task with X
    }

    /** Convert variable done to string value for saving purposes.
     *
     * @return 1 or 0 depending on variable done
     * */
    public String getStatusNum(){
        return (this.done? "1" : "0");
    }

    /** Getter for variable escription
     *
     * @return description String
     * */
    public String getDescription() {
        return this.description;
    }

    /**
     * Print the desciprtion together with the string formatted
     * variable done
     *
     * @return String value
     */
    public String toString() {
        return '['+ getStatusIcon() + "] " +
                description;
    }

    /**
     * To format the Event tasks into a string format in the preferred separator.
     * Used for Saving purposes.
     *
     * @param separator String
     * @return String value
     */
    public String toStringSaveTask(String separator){
        return getStatusNum() + separator + getDescription();
    }

}
