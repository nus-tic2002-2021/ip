package duke.ui;

import duke.tasklist.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * An <code>Ui</code> object to interact with user.
 */
public class Ui {

    private Scanner userInput = new Scanner(System.in);

    /** Returns user command as a String object.
     *
     * @return User command.
     * */
    public String readCommand() {
        return userInput.nextLine();
    }

    /** Prints welcome message. */
    public void showWelcome() {
        System.out.print("___________________________________________________________________\n"
                        + "Hello! I'm Duke By Justin ☺\nWhat can I do for you?\n"
                        + " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n"
                        + "___________________________________________________________________\n");
    }

    /** Prints divider line. */
    public void showDividerLine() {
        System.out.println("___________________________________________________________________");
    }

    /** Prints exception message - file not found. */
    public void showFileNotFound() {
        System.out.println("No task list found. I will create one for you now! ☺");
    }

    /**
     * Prints exception message.
     *
     * @param message The exception message to be printed.
     */
    public void showException(String message) { System.out.println(message); }

    /** Prints exit message. */
    public void showExit() { System.out.println("Bye. Hope to see you again soon!"); }

    /**
     * Prints the added task.
     *
     * @param task The added task.
     * @param size The size of task list.
     */
    public void printAddedTask(String task, int size) {
        System.out.println("Got it! I've added this task.\n" + task + "\nNow you have " + size + " in the list.");
    }

    /**
     * Prints the completed task.
     *
     * @param task The completed task.
     */
    public void printCompletedTask(String task) {
        System.out.println("Nice! I've marked this task as done.\n" + task);
    }

    /**
     * Prints the deleted task.
     *
     * @param task The deleted task.
     * @param size The size of task list.
     */
    public void printDeletedTask(String task, int size) {
        System.out.println("Noted! I've removed this task.\n" + task + "\nNow you have " + size + " in the list.");
    }

    /**
     * Prints all tasks in the list.
     *
     * @param taskList The ArrayList of Tasks to be printed.
     */
    public void printTaskList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("You have no task in your list!");
        }
        else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println(i+1 + ". " + task.toString());
            }
        }
    }

}
