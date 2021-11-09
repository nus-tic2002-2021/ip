package Duke;

import Duke.Models.*;
import Duke.Ui.Ui;

import java.util.ArrayList;

/**
 * Represents the list that contains the task inputted by using Duke.
 *
 */
public class TaskList {
    static ArrayList<Task> DukeList = new ArrayList<>();

    /**
     * Returns the number of tasks inside the task list.
     * @return int of the size of task list.
     */
    public static int size() {
        return DukeList.size();
    }

    /**
     * Takes in a new Task
     * and adds it to the task list.
     * @param newEntry
     */
    public static void addTaskToList(Task newEntry) {
        DukeList.add(newEntry);
    }

    /**
     * Takes in a index and removes the task at the index from the task list.
     * @param index
     */
    public static void RemoveTask(int index) {
        DukeList.remove(index);
    }

    /**
     * Prints all the task in the task list with their information.
     */
    public static void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < DukeList.size(); i++) {
            System.out.println((i + 1) + "." + DukeList.get(i).getTaskInfo());
        }
    }

    /**
     * Takes in a valid "done" input and gets the index.
     * If index is valid, marks the task at that index of the list as completed.
     *
     * @param input
     * @throws DukeException
     */
    public static void markTaskAtIndex(String input) throws DukeException {
        int index = Integer.parseInt(input.substring(4).trim()) - 1;
        if (index < DukeList.size() && index > -1) {
            MarkTask(DukeList.get(index));
            System.out.println("Nice! I've marked this task as done:\n  "
                    + DukeList.get(index).getTaskInfo());
        } else {
            throw new DukeException("☹ OOPS!!! " +
                    "The index number of the task to be done is invalid!");
        }
    }

    /**
     * Mark the task given as a parameter as completed.
     * @param inputTask
     */
    public static void MarkTask(Task inputTask) {
        inputTask.markCompleted();
    }

    /**
     * Takes in a valid "delete" input and gets the index.
     * If index is valid, the task at that index of the list is deleted from the list.
     *
     * @param input
     * @throws DukeException
     */
    public static void DeleteIndex(String input) throws DukeException {
        int index = Integer.parseInt(input.substring(6).trim()) - 1;
        if (index < DukeList.size() && index > -1) {
            String DeletedInfo = DukeList.get(index).getTaskInfo();
            RemoveTask(index);
            System.out.println("Noted! I've removed this task:\n  " + DeletedInfo);
            Ui.PrintTaskCount();
        } else {
            throw new DukeException("☹ OOPS!!! The index number of the task to delete is invalid!");
        }
    }

    public static void printTaskWithDesc(String desc) {
        int count = 0;
        for (int i = 0; i < TaskList.size(); i++) {
            Task currentTask = DukeList.get(i);
            if (currentTask.getDescription().toLowerCase().contains(desc.toLowerCase())) {
                if (count == 0) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                count++;
                System.out.println(count + ". " + currentTask.getTaskInfo());
            }
        }
        if (count == 0) {
            System.out.println("There are no matching tasks in your list.");
        }
    }
}
