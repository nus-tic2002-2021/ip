package tasks;

import exceptions.*;
import java.util.ArrayList;

/**
 * A <code>TaskList</code> object stores all available tasks (to-dos, deadlines, events).
 */
public class TaskList {
    private final ArrayList<Task> taskList;
    private final Scheduler scheduler;

    public TaskList() {
        taskList = new ArrayList<>();
        scheduler = new Scheduler();
    }

    /**
     * Custom constructor of <code>TaskList</code>.
     * To be used when loading tasks from an existing record.
     *
     * @param tasks ArrayList of Task objects to be loaded.
     */
    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
        scheduler = new Scheduler();
        scheduler.updateSchedule(tasks);
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
        } else {
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
        } else {
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
    public boolean addTask(Task task){
        boolean canBeAdded = true;
        if(task.getClass().equals(EventTask.class)) {
            canBeAdded = scheduler.schedule(((EventTask) task).start, ((EventTask) task).end);
        }
        if(canBeAdded) {
            taskList.add(task);
        }
        return canBeAdded;
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
