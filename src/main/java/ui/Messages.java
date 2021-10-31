package ui;

import tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public final class Messages {

    public static final String WELCOME_MSG = "Hello, I'm Duke!\n" + "What can I do for you?\n" +
            "Ps: Type 'help' for more information.\n";

    public static final String HELP_MSG = "View all existing tasks:     list\n" +
        "Add a new to-do:             todo {taskDescription}\n" +
        "Add a new deadline:          deadline {taskDescription} /by {yyyy-MM-dd HHmm}\n" +
        "Add a new event:             event {taskDescription} /at {yyyy-MM-dd HHmm} to {yyyy-MM-dd HHmm}\n" +
        "Mark a task as completed:    done {taskId}\n" +
        "Delete a task:               delete {taskId}\n" +
        "Fine task by keyword:        find {keyword}\n" +
        "Check schedule of a date:    view {yyyy-MM-dd}\n" +
        "Exit the program:            bye\n";

    public static final String LOADING_ERROR = "No record found. Creating new file...\n";

    public static final String ADDED_MSG = "Got it! I've added this task:\n";

    public static final String COMPLETED_MSG = "Amazing! The task is marked as done now:\n";

    public static final String DELETED_MSG = "Noted. I've removed this task:\n";

    public static final String LISTED_MSG = "Here are the task(s) in your list:\n";

    public static final String SEARCH_COMPLETED_MSG = "Here are the matching task(s) in your list:\n";

    public static final String EXIT_MSG = "Bye. Hope to see you again soon!\n";

    public static String getScheduledMsg(LocalDate date){
        return "Here are the task(s) scheduled on " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + ":\n";
    }

    public static String getTaskCountMsg(int listSize) { return "Now you have " + listSize + " task(s) in the list\n"; }

    public static String getTaskMsg(String task) { return task + "\n"; }

    public static String getAllTasksMsg(ArrayList<Task> taskList) {
        if (taskList.size() == 0){
            return "There's no task now :D\n";
        } else {
            String taskStr = "";
            int index = 1;
            for (Task task : taskList) {
                taskStr += index + ". " + task + "\n";
                index++;
            }
            return taskStr;
        }
    }
}
