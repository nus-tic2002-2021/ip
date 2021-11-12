package duke.task;

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
    protected TaskPriority taskPriority;

    /**
     * Create an abstract Task object
     */
    public Task(String taskDetail) {
        this.taskDetail = taskDetail;
        this.taskPriority = TaskPriority.LOW;
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


    /**
     * Return the TaskPriority
     *
     * @return TaskPriority that represents the priority of task
     */
    public TaskPriority getTaskPriority() {
        return taskPriority;
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

    /**
     * Set thr priority of a task
     *
     * @param taskPriority TaskPriority that represent the new priority
     */
    public void setPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }
}
