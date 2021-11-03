package task;

/**
 * Abstract class which all other Task classes extend (Event, deadline etc.).
 */
public abstract class Task {
    protected String taskDesc;
    protected boolean isDone;
    protected boolean isDeleted;

    /**
     * Constructor method of Task.
     * @param taskDesc contains information of Task, includes task name and may include date and time.
     */
    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isDone = false;
        this.isDeleted = false;
    }

    /**
     * Calls when execute method of findCommand is called.
     * Checks to see if description of task contains keyword.
     * @param keyword word/phrase to find in description.
     * @return boolean value indicating if word/phrase can be found in description.
     */
    public boolean findWord(String keyword) {
        if (taskDesc.indexOf(keyword) == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Calls in toString method. Indicates status of completion of task.
     * @return //+ if done, else -
     */
    public String getStatus() {
        return (isDone ? "+" : "-");
    }

    /**
     * sets isDone value to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String toString();
}
