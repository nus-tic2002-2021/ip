package Duke.Models;

import Duke.DukeException;

/**
 * Represents a Task with information regarding the
 * description of the task and whether it is completed.
 */
public class Task {

    protected String Description;
    protected boolean Completed;

    /**
     * Task constructor method that takes in a string as description
     * and sets task as uncompleted.
     * @param newTask
     */
    public Task(String newTask) {
        this.Description = newTask;
        this.Completed = false;
    }

    /**
     * Task constructor method that takes in two strings
     * one as the description of the task
     * and the other as an indication whether task is completed.
     * @param newTask
     * @param toComplete
     * @throws DukeException
     */
    public Task(String newTask, String toComplete) throws DukeException {
        this.Description = newTask;
        if (toComplete.equals("1")){
            this.Completed = true;
        } else if (toComplete.equals("0")) {
            this.Completed = false;
        } else {
            throw new DukeException("Invalid input");
        }
    }

    /**
     * Returns description of the task as a string.
     * @return String with description of the task.
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Returns a boolean variable based on whether the task is completed.
     * @return boolean
     */
    public boolean isCompleted() {
        return Completed;
    }

    /**
     * Gets additional info from task object if any.
     * @return String with additional info.
     */
    public String getAdditionalInfo() {
        return "";
     }

    /**
     * Returns a "[X]" symbol as a string if task is completed.
     * If uncompleted, return "[ ]" as a string.
     * @return String indicating whether a task is completed.
     */
    public String getCompletedSymbol() {
        if (Completed) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Returns string with task information regarding completion and description.
     * @return String with information on Task.
     */
    public String getTaskInfo() {
        String TaskInfo = this.getCompletedSymbol() + " " + Description;
        return TaskInfo;
    }

    /**
     * Marks "Completed" variable as true.
     */
    public void markCompleted() {
        Completed = true;
    }

    /**
     * Gets the task type of a task object.
     * @return String of initials of task type in any.
     */
    public String getTaskType() {
        return "";
    }
}
