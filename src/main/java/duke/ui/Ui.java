package duke.ui;

import duke.tasklist.Task;

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
     */
    public String readCommand() {
        return userInput.nextLine();
    }

    /** Prints command header. */
    public void showCommandHeader() {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\ncmd: ");
    }

    /** Prints welcome message. */
    public void showWelcome() {
        showDividerLine();
        System.out.println("Hello! I'm Duke By Justin ☺\nWhat can I do for you?\n" +
                        " ____        _        \n" +
                        "|  _ \\ _   _| | _____ \n" +
                        "| | | | | | | |/ / _ \\\n" +
                        "| |_| | |_| |   <  __/\n" +
                        "|____/ \\__,_|_|\\_\\___|\n" +
                        "\nCOMMAND LIST:\n" +
                        "1. list\n" +
                        "2. todo <task description>\n" +
                        "3. event <task description> /at <yyyy-mm-dd hh:mm>\n" +
                        "4. deadline <task description> /by <yyyy-mm-dd hh:mm>\n" +
                        "5. done <task id>\n" +
                        "6. delete <task id>\n" +
                        "7. find <keyword>\n" +
                        "8. tag <task id> <tag description>\n" +
                        "9. bye");
    }

    /** Prints divider line. */
    public void showDividerLine() {
        System.out.println("_______________________________________________________________________________________" +
                "__________________________________");
    }

    /** Prints exception message - file not found. */
    public void showFileNotFound() {
        System.out.println("\nNOTE: No task list found. I will create one for you!");
    }

    /**
     * Prints exception message.
     *
     * @param message The exception message to be printed.
     */
    public void showException(String message) {
        System.out.println(message);
    }

    /** Prints exit message. */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the added task.
     *
     * @param task The added task.
     * @param size The size of task list.
     */
    public void printAddedTask(String task, int size) {
        System.out.println("Got it! I've added this task.\n " + task + "\nNow you have " + size + " in the list.");
    }

    /**
     * Prints the completed task.
     *
     * @param task The completed task.
     */
    public void printCompletedTask(String task) {
        System.out.println("Nice! I've marked this task as done.\n " + task);
    }

    /**
     * Prints the deleted task.
     *
     * @param task The deleted task.
     * @param size The size of task list.
     */
    public void printDeletedTask(String task, int size) {
        System.out.println("Noted! I've removed this task.\n " + task + "\nNow you have " + size + " in the list.");
    }

    /**
     * Prints all tasks in the list.
     *
     * @param taskList The ArrayList of Tasks to be printed.
     */
    public void printTaskList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("You have no task in your list!");
            return;
        }
        System.out.println("Here are the tasks in your list!");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println(i+1 + ". " + task.toString());
        }
    }

    /**
     * Prints all matching tasks in the list.
     *
     * @param taskList The ArrayList of Tasks to find the task(s) with the keyword.
     * @param keyword The keyword to find the task(s).
     */
    public void printMatchingTasks(ArrayList<Task> taskList, String keyword) {

        boolean isTaskFound = false;
        int i = 0;

        for (i = i; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                isTaskFound = true;
                break;
            }
        }

        if ( isTaskFound ) {
            System.out.println("Here are the matching tasks in your list!");
            for ( i = i ; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                    System.out.println(i+1 + ". " + task.toString());
                }
            }
        } else if (taskList.size() == 0) {
            System.out.println("You have no task in your list!");
        } else {
            System.out.println("There is no task found!");
        }

    }

    /**
     * Prints the tagged task.
     *
     * @param task The tagged task.
     */
    public void printTaggedTask(String task) {
        System.out.println("Nice! I've tagged this task.\n " + task);
    }

    /**
     * Prints the un-tagged task.
     *
     * @param task The un-tagged task.
     */
    public void printUnTaggedTask(String task) {
        System.out.println("Nice! I've un-tagged this task.\n " + task);
    }

}
