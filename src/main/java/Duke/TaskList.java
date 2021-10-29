package Duke;

import Duke.Models.*;
import Duke.Ui.Ui;

import java.util.ArrayList;

public class TaskList {
    static ArrayList<Task> DukeList = new ArrayList<>();

    public static int size() {
        return DukeList.size();
    }

    public static void addTaskToList(Task newEntry) {
        DukeList.add(newEntry);
    }

    public static void RemoveTask(int index) {
        DukeList.remove(index);
    }

    public static void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < DukeList.size(); i++) {
            System.out.println((i + 1) + "." + DukeList.get(i).getTaskInfo());
        }
    }

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

    public static void MarkTask(Task inputTask) {
        inputTask.markCompleted();
    }

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
}
