package ui;

import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * An <code>Ui</code> object deals with interactions with the user.
 * Prints response in the Terminal based on user's inputs.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /** Prints Duke logo and Hello message. */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("__________________________________________");
        System.out.println("Hello, I'm Duke!" );
        System.out.println("What can I do for you?");
        System.out.println("__________________________________________");
    }

    /** Prints separator line. */
    public void showLine() {
        System.out.println("__________________________________________");
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
        System.out.println("Error: " + message);
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
    public void printTask (String task) { System.out.println(task); }

    /**
     * Prints all tasks in the list.
     *
     * @param taskList ArrayList of Tasks to be printed
     */
    public void printAllTasks(ArrayList<Task> taskList) {
        if(taskList.size() == 0){
            System.out.println("There's no task now :D");
        }
        else {
            int index = 1;
            for (Task task : taskList) {
                System.out.println(index + ". " + task);
                index++;
            }
        }
    }
}
