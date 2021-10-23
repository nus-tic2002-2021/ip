package duke.task.model;


/**
 * Root class for tasks
 */
public abstract class Task implements Comparable<Task> {


    private String taskDescription;
    private Integer taskId;
    private Boolean done;

    protected Task() {
    }

    protected Task(String taskDescription, Integer taskId, Boolean done) {
        this.setTaskDescription(taskDescription);
        this.setTaskId(taskId);
        this.setDoneStatus(done);
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Integer getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Boolean isDone() {
        return this.done;
    }

    public Boolean setDoneStatus(Boolean next) {
        this.done = next;
        return this.done;
    }

    /**
     * Check if this task description contains keyword.
     * @param keyword
     * @return
     */
    public Boolean descContainsKeyword(String keyword) {
        String desc = this.getTaskDescription();
        String[] words = desc.split("\\s+");
        for (String word : words) {
            if (word.toLowerCase().equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    public abstract String getChronologyString();

    public int compareTo(Task u) {
        return Integer.compare(this.getTaskId(), u.getTaskId());
    }
}
