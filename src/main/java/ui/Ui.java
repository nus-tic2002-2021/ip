package ui;

import tasks.Task;
import tasks.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

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

    public void showLine() {
        System.out.println("__________________________________________");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("No record found. Creating new file...");
    }

    public void showError(String message){
        System.out.println("Error: " + message);
    }

    public void showAdded() { System.out.println("Got it! I've added this task: "); }

    public void showCompleted() { System.out.println("Amazing! The task is marked as done now:"); }

    public void showDeleted() { System.out.println("Noted. I've removed this task: "); }

    public void showExit() { System.out.println("Bye. Hope to see you again soon!"); }

    public void printTaskCount(int listSize) { System.out.println("Now you have " + listSize + " task(s) in the list"); }

    public void printTask (String task) { System.out.println(task); }

    public void printAllTasks(ArrayList<Task> taskList) {
        if(taskList.size() == 0){
            System.out.println("There's no task now :D");
        }
        else {
            System.out.println("Here are the task(s) in your list:");

            int index = 1;
            for (Task task : taskList) {
                System.out.println(index + ". " + task);
                index++;
            }
        }
    }

}
