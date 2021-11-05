package ui;

import tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * An <code>Ui</code> object deals with interactions with the user.
 * Prints response in the Terminal based on user's inputs.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /** Prints Duke logo and Hello message. */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("____________________________________________________________________________________");
        System.out.println("Hello, I'm Duke!" );
        System.out.println("What can I do for you?");
        System.out.println("Ps: Type 'help' for more information.");
        System.out.println("____________________________________________________________________________________");
    }

    /** Prints separator line. */
    public void showLine() {
        System.out.println("____________________________________________________________________________________");
    }

    /** Prints all command instructions. */
    public void showHelp(){
        System.out.println("View all existing tasks:     list");
        System.out.println("Add a new to-do:             todo {description}");
        System.out.println("Add a new deadline:          deadline {description} /by {yyyy-MM-dd HHmm}");
        System.out.println("Add a new event:             event {description} /at {yyyy-MM-dd HHmm} -to {yyyy-MM-dd HHmm}");
        System.out.println("Mark a task as completed:    done {taskId}");
        System.out.println("Delete a task:               delete {taskId}");
        System.out.println("Fine task by keyword:        find {keyword}");
        System.out.println("Check schedule of a date:    view {yyyy-MM-dd}");
        System.out.println("Exit the program:            bye");
    }

    /** Reads user's input command. */
    public String readCommand() {
        return sc.nextLine();
    }

    /** Prints loading error message (file not found). */
    public void showLoadingError() {
        System.out.println("No record found. Creating new file...");
    }

    /**
     * Prints Duke error message.
     *
     * @param message Error message to be printed.
     */
    public void showError(String message){
        System.out.println(message);
    }

    /** Prints task added successful message. */
    public void showAdded() { System.out.println("Got it! I've added this task:"); }

    /** Prints task completed successful message. */
    public void showCompleted() { System.out.println("Amazing! The task is marked as done now:"); }

    /** Prints task deleted successful message. */
    public void showDeleted() { System.out.println("Noted. I've removed this task:"); }

    /** Prints tasks listed message. */
    public void showListed() { System.out.println("Here are the task(s) in your list:"); }

    /** Prints search completed message. */
    public void showSearchCompleted() { System.out.println("Here are the matching task(s) in your list:"); }

    /** Prints exit message. */
    public void showExit() { System.out.println("Bye. Hope to see you again soon!"); }

    public void showScheduleFor(LocalDate date) {
        System.out.println("Here are the task(s) scheduled on " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + ":");
    }

    /**
     * Prints current task count.
     *
     * @param listSize Number of tasks in the list
     */
    public void printTaskCount(int listSize) { System.out.println("Now you have " + listSize + " task(s) in the list"); }

    /**
     * Prints single task.
     *
     * @param task Task to be printed
     */
    public void printTask(String task) { System.out.println(task); }

    /**
     * Prints all tasks in the list.
     *
     * @param taskList ArrayList of Tasks to be printed
     */
    public void printAllTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 0){
            System.out.println("There's no task now :D");
        } else {
            int index = 1;
            for (Task task : taskList) {
                System.out.println(index + ". " + task);
                index++;
            }
        }
    }
}
