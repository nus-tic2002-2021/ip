package ui;

import command.ViewScheduleCommand;
import task.Task;
import task.TaskList;

import java.util.Scanner;

/**
 * Determines what is displayed to user.
 */
public class Ui {
    Scanner sc = new Scanner(System.in);

    /**
     * Calls when execute method of ExitCommand is called.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints border.
     */
    public void showLine() {
        String output = "";
        for (int n = 0; n < 50; n++) {
            output += "_";
        }
        System.out.println(output);
    }

    /**
     * Calls when a task is entered by user.
     * Calls when execute of AddTask is called.
     * @param newTask task entered.
     * @param taskNum number of tasks in Task List.
     */
    public void readTask(Task newTask, int taskNum) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
    }

    /**
     * Displays delete message.
     * Calls when execute of DeleteCommand is called.
     * @param removeTask for task to be removed.
     * @param taskNum number of tasks in the Task List.
     */
    public void readDelete(Task removeTask, int taskNum) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(removeTask.toString());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
    }

    /**
     * Displays message when a user finishes a task.
     * Calls when execute of EditCommand is called.
     * @param completeTask Task which has been marked as done.
     */
    public void readDone(Task completeTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(completeTask.toString());
    }

    /**
     * Displays contents of Task List.
     * Calls when execute of ListCommand is called.
     * @param tasks TaskList
     */
    public void readList(TaskList tasks) {
        System.out.println("Here are the task(s) in your list: ");
        System.out.println(tasks);
    }

    /**
     * Reads user input.
     * @return String to be parsed by parser.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays message when DukeException is caught.
     * @param errorMsg DukeException e.getMessage()
     */
    public void showError(String errorMsg) {
        System.out.println("Your error message: " + errorMsg);
    }

    /**
     * Calls when Duke Exception is thrown when instantiating Task List and/ or Storage Objects.
     * Calls by run method in Duke class.
     */
    public void showLoadingError() {
        System.out.println("Loading Error!");
    }

    /**
     * Displays matching Tasks.
     * Calls when execute method of FindCommand is called.
     * @param tasks TaskList of tasks with keyword.
     */
    public void showMatches(TaskList tasks) {
        System.out.println("Here are the matching task(s) in your list: ");
        System.out.println(tasks);
    }

    /**
     * Displays tasks in the form of a schedule e.g., view the schedule for a specific date
     * Calls when execute method of ViewScheduleCommand is called.
     * @param tasks TaskList of tasks with schedule.
     */
    public void showSchedule(TaskList tasks) {
        System.out.println("Here is the schedule for the list of task(s) on the mentioned date: ");
        System.out.println(tasks);
    }

    /**
     * Displays all tasks with schedule.
     * Calls when execute method of ViewScheduleCommand is called.
     * @param tasks TaskList of tasks with schedule.
     */
    public void showAllSchedule(TaskList tasks) {
        System.out.println("Here is the list of task(s) that has schedule: ");
        System.out.println(tasks);
    }

    /**
     * Displays the full set of instructions to the user so that user able to see all commands available in one view.
     */
    public void readInstruction() {
        System.out.println("This is the list of commands available currently. Please replace the details in ().");
        System.out.println("\n1. todo (details)");
        System.out.println("\n2. deadline (details) /by (date)d/mm/yyyy (time)HHmm.");
        System.out.println("\n3. event (details) /at (date)d/mm/yyyy (start time)HHmm-(end time)HHmm.");
        System.out.println("\n4. list -- This will show the list of tasks you have on hand.");
        System.out.println("\n5. done (Key in the index number of the task that you have completed).");
        System.out.println("\n6. delete (Key in the index number of the task that you wish to delete).");
        System.out.println("\n7. find (Key in the keyword that you wish to search in the list of the task).");
        System.out.println("\n8. view (Key in the specific date to view the tasks you have in the schedule).");
        System.out.println("\n9. view all -- This will display all the tasks with schedule.");
        System.out.println("\n10. bye -- To close off the application and save the tasks in tasks.txt file.");
        System.out.println("\nHope this helps!");
    }
}