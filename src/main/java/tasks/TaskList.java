package tasks;

import exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

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
        scheduler.loadSchedule(tasks);
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
        int taskIndex = taskId - 1;
        boolean isOutOfRange = taskId > taskList.size() || taskId < 1;

        if (isOutOfRange) {
            throw new DukeException("Task with id " + taskId + " is not found.");
        }
        Task doneTask = taskList.get(taskIndex);
        doneTask.markAsDone();
        return doneTask.toString();
    }

    /**
     * Gets task by id and delete the task.
     * Returns the deleted task in String format.
     *
     * @param taskId ID of the task to be deleted.
     * @throws DukeException If task with the specific ID is not found.
     */
    public String deleteTask(int taskId) throws DukeException {
        boolean isOutOfRange = taskId > taskList.size() || taskId < 1;

        if (isOutOfRange) {
            throw new DukeException("Task with id " + taskId + " is not found.");
        }
        Task deletedTask = taskList.get(taskId - 1);
        taskList.remove(taskId - 1);

        boolean isEvent = deletedTask.getClass().equals(EventTask.class);
        if (isEvent) {
            scheduler.freeUpSlot(((EventTask) deletedTask).start);
        }
        return deletedTask.toString();
    }

    /**
     * Adds a new task into the TaskList.
     *
     * @param task Task to be added.
     */
    public boolean addTask(Task task) {
        boolean isEvent = task.getClass().equals(EventTask.class);
        boolean canBeAdded = true;
        if (isEvent) {
            EventTask event = (EventTask) task;
            canBeAdded = scheduler.schedule(event.description, event.start, event.end);
        }
        if (canBeAdded) {
            taskList.add(task);
        }
        return canBeAdded;
    }

    /**
     * Searches whether a task with input keyword exists in the TaskList.
     *
     * @param keyword Search keyword.
     */
    public ArrayList<Task> searchTask(String keyword) {
        ArrayList<Task> results = new ArrayList<>();

        for (Task task: taskList) {
            String description = task.description.toLowerCase();
            if (description.contains(keyword.toLowerCase())) {
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

    public ArrayList<Task> getTaskSchedule(LocalDate date) {
        ArrayList<Task> schedule = new ArrayList<>();
        ArrayList<EventTask> events = scheduler.getSchedule(date);

        for (EventTask event: events) {
            schedule.add(event);
        }
        return schedule;
    }
}
