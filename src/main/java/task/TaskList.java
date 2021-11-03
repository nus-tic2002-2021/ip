package task;

import exception.DukeException;
import filewriter.Storage;
import java.util.ArrayList;

/**
 * Object containing data structure to store Tasks.
 */
public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();
    public int taskNum;
    public boolean isFirst;

    /**
     * Constructor for Task List.
     * Calls when generating Task List from txt file.
     * @param storage Contains ArrayList of Task specified from txt file.
     */
    public TaskList(Storage storage) {
        try {
            this.taskList = storage.getSchedule();
            taskNum = taskList.size();
            if (taskNum == 0) {
                isFirst = true;
            } else {
                isFirst = false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Constructor for Task List.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
        taskNum = taskList.size();
        if (taskNum == 0) {
            isFirst = true;
        } else {
            isFirst = false;
        }
    }


    /**
     * Constructor for Task List.
     * @param schedule ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> schedule) {
        this.taskList = schedule;
        taskNum = taskList.size();
        if (taskNum == 0) {
            isFirst = true;
        } else {
            isFirst = false;
        }
    }

    /**
     * Gets Task corresponding to specified index.
     * @param index Indicates which task in the task list to return.
     * @return Task as specified by index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Marks a Task in task list as done.
     * @param index Specifies which task to mark as done.
     * @return completed Task.
     * @throws NullPointerException Throws when an application attempts
     *                              to use null in a case where an object is required.
     * @throws IndexOutOfBoundsException Throws to indicate that an index of
     *                                   some sort (such as to an array, to a string, or to a vector) is out of range.
     * @throws NumberFormatException Throws to indicate that the application has attempted to convert a string to
     *                               one of the numeric types, but that the string does not have appropriate format.
     * @throws DukeException Wraps exception. Helps to check if
     *                       exception has been accounted for. Catches exception and throws DukeException.
     */
    public Task complete(int index) throws NullPointerException, IndexOutOfBoundsException,
            NumberFormatException, DukeException {
        Task completedTask = taskList.get(index);
        completedTask.markAsDone();
        return completedTask;
    }

    /**
     * Adds new Task to TaskList.
     * @param task to add a new task.
     * @throws DukeException if unexpected error occurs.
     */
    public void addTask(Task task) throws DukeException {
        taskList.add(task);
        taskNum++;
    }

    /**
     * Deletes Task from TaskList.
     * @param index based on the index number.
     * @return deleted Task.
     * @throws NullPointerException Throws when an application attempts
     *                              to use null in a case where an object is required.
     * @throws IndexOutOfBoundsException Throws to indicate that an index of
     *                                   some sort (such as to an array, to a string, or to a vector) is out of range.
     * @throws NumberFormatException Throws to indicate that the application has attempted to convert a string to
     *                               one of the numeric types, but that the string does not have appropriate format.
     * @throws DukeException Wraps exception. Helps to check if
     *                       exception has been accounted for. Catches exception and throws DukeException.
     */
    public Task remove(int index) throws NullPointerException, IndexOutOfBoundsException,
            NumberFormatException, DukeException {
        Task removeTask = taskList.get(index);
        taskList.remove(index);
        taskNum--;
        return removeTask;
    }

    /**
     * Retrieves Task schedule from TaskList array.
     * Calls when execute method of FindCommand is called.
     * @return Task schedule from TaskList array.
     */
    public ArrayList<Task> getList() {
        return taskList;
    }

    /**
     * toString method of TaskList.
     * @return contents of TaskList. i.e. tasks.
     */
    public String toString() {
        String output = "";
        for (int idx = 0; idx < taskNum; idx++) {
            Task task = taskList.get(idx);
            output += ((idx + 1) + "." + task.toString() + "\n");
        }
        return output.substring(0, output.length() - 1);
    }

    /**
     * Resets the entire list and start fresh.
     */
    public void reset() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Converts data in taskList to the appropriate String format to be stored in text file.
     * @return String text.
     */
    public String toText() {
        String output = "";
        if (taskNum != 0) {
            for (int index = 0; index < taskNum; index++) {
                Task task = getTask(index);
                output += (task.toString() + "\n");
            }
            return output.substring(0, output.length() - 1);
        } else {
            return output;
        }
    }
}


