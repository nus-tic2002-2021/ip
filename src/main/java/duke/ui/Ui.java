package duke.ui;

import duke.tasklist.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner userInput = new Scanner(System.in);

    public String readCommand() {
        return userInput.nextLine();
    }

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

    public void showDividerLine() {
        System.out.println("___________________________________________________________________");
    }

    public void showFileNotFound() {
        System.out.println("No task list found. I will create one for you now! ☺");
    }

    public void showException(String message) { System.out.println(message); }

    public void showExit() { System.out.println("Bye. Hope to see you again soon!"); }

    public void printAddedTask(String task, int size) {
        System.out.println("Got it! I've added this task.\n" + task + "\nNow you have " + size + " in the list.");
    }

    public void printCompletedTask(String task) {
        System.out.println("Nice! I've marked this task as done.\n" + task);
    }

    public void printDeletedTask(String task, int size) {
        System.out.println("Noted! I've removed this task.\n" + task + "\nNow you have " + size + " in the list.");
    }

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
