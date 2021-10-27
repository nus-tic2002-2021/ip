package duke.tasklist;

import duke.exception.*;

import java.util.ArrayList;

/**
 * A <code>TaskList</code> object to store an arraylist of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructs new TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructs TaskList with this stored task list.
     *
     * @param taskList The stored task list.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds this task to list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task){
        taskList.add(task);
    }

    /**
     * Deletes this task from list and returns its data as a String object.
     *
     * @param taskId The task id to delete.
     * @return A String object containing the data of the deleted task.
     * @throws DukeException If task id is less than 1 or more than the size of the task list.
     */
    public String deleteTask(int taskId) throws DukeException {
        if (taskId > taskList.size() || taskId < 1) {
            throw new DukeException("Oops! Please specify the correct task id to remove it ☹");
        }
        else {
            Task deletedTask = taskList.get(taskId - 1);
            taskList.remove(taskId - 1);
            return deletedTask.toString();
        }
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of the task list.
     */
    public int getSize() { return taskList.size(); }

    /**
     * Returns all the tasks as an arraylist of tasks.
     *
     * @return The task list.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Marks this task as done and returns its data as a String object.
     *
     * @param taskId The task id to mark as done.
     * @return A String object containing the data of the task that was done.
     * @throws DukeException If task id is less than 1 or more than the size of the task list.
     */
    public String setDone(int taskId) throws DukeException {
        if (taskId > taskList.size() || taskId < 1) {
            throw new DukeException("Oops! Please specify the correct task id to mark it as done ☹");
        } else {
            Task completedTask = taskList.get(taskId - 1);
            completedTask.setDone();
            return completedTask.toString();
        }
    }

    /**
     * Tags this task id with this description and returns its data as a String object.
     *
     * @param taskId The task id to be tagged.
     * @param tagDesc The tag description.
     * @return A String object containing the data of the task that was tagged.
     * @throws DukeException If task id is less than 1 or more than the size of the task list.
     */
    public String setTag(int taskId, String tagDesc) throws DukeException {
        if (taskId > taskList.size() || taskId < 1) {
            throw new DukeException("Oops! Please specify the correct task id to tag it ☹");
        } else {
            Task taggedTask = taskList.get(taskId - 1);
            taggedTask.setTag(tagDesc);
            return taggedTask.toString();
        }
    }

}
