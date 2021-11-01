package java.task;

/**
 * Abstract class for Task
 * <p>
 * A task object correspond to a single task object.
 * The class object can be of the type todo, event, or deadline.
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public abstract class Task {
    protected String taskDetail;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String taskDetail) {
        this.taskDetail = taskDetail;
    }

    // Getter <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Return the task detail
     *
     * @return String that represents the task detail
     */
    public String getTaskDetail() {
        return taskDetail;
    }

    /**
     * Return the boolean done status that represents if a task is done
     *
     * @return boolean that represents the done status of a task
     */
    public boolean getDoneStatus() {
        return isDone;
    }

    /**
     * Return the TaskType
     *
     * @return TaskType that represents the type of task
     */
    public TaskType getTaskType() {
        return taskType;
    }

    // Setter <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Modified the task detail
     *
     * @param modifiedTaskDetail String that represent the new task detail
     */
    public void setTask(String modifiedTaskDetail) {
        taskDetail = modifiedTaskDetail;
    }

    /**
     * Set the task as completed
     * Change the done status to completed
     */
    public void setTaskCompleted() {
        isDone = true;
    }

    /**
     * Modified the type of the task
     *
     * @param taskType String that represent the new task type
     */
    public void setTypeOfTask(TaskType taskType) {
        this.taskType = taskType;
    }

}
