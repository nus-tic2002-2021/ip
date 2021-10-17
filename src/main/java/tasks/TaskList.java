package tasks;

import exceptions.*;
import java.util.ArrayList;

/**
 * A <code>TaskList</code> object stores all available tasks (to-dos, deadlines, events).
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Custom constructor of <code>TaskList</code>.
     * To be used when loading tasks from an existing record.
     *
     * @param tasks ArrayList of Task objects to be loaded.
     */
    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    /** Returns the list size (task count) of the TaskList. */
    public int getListSize() { return taskList.size(); }

    /**
     * Get task by id and set the status of the task to "done".
     * Returns the completed task in String format.
     *
     * @param taskId ID of the completed task.
     * @throws DukeException If task with the specific ID is not found.
     */
    public String setDone(int taskId) throws DukeException {
        if (taskId > taskList.size() || taskId < 1){
            throw new DukeException("Task with id " + taskId + " is not found.");
        }
        else {
            Task doneTask = taskList.get(taskId - 1);
            doneTask.markAsDone();
            return doneTask.toString();
        }
    }

    /**
     * Get task by id and delete the task.
     * Returns the deleted task in String format.
     *
     * @param taskId ID of the task to be deleted.
     * @throws DukeException If task with the specific ID is not found.
     */
    public String deleteTask(int taskId) throws DukeException {
        if (taskId > taskList.size() || taskId < 1){
            throw new DukeException("Task with id " + taskId + " is not found.");
        }
        else {
            Task deletedTask = taskList.get(taskId - 1);
            taskList.remove(taskId - 1);
            return deletedTask.toString();
        }
    }

    /**
     * Add a new task into the TaskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task){
        taskList.add(task);
    }

    /**
     * Search whether a task with input keyword exists in the TaskList.
     *
     * @param keyword Search keyword.
     */
    public ArrayList<Task> searchTask(String keyword) {
        ArrayList<Task> results = new ArrayList<>();

        for (Task task: taskList) {
            if(task.description.contains(keyword)){
                results.add(task);
            }
        }
        return results;
    }

    /**
     * Returns all tasks in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return taskList;
    }
}
