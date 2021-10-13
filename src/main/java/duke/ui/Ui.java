package duke.ui;

import duke.parser.Parser;
import duke.tasklist.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner input = new Scanner(System.in);

    protected final static String LINE = "______________________________________________________________\n";
    protected final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    protected final static String INTRO = "Hello! I'm Duke By Justin ☺\nWhat can I do for you?\n" + LOGO;
    protected final static String INVALID_CMD = "Oops! Sorry, I don't know what you mean ☹\n";
    protected final static String BYE = "Bye. Hope to see you again soon!\n";
    protected final static String NO_TASK = "You have no task in your list!\n";
    protected final static String INVALID_DONE = "Oops! Please specify the correct task id to mark it as done ☹\n";
    protected final static String INVALID_DELETE = "Oops! Please specify the correct task id to remove it ☹\n";
    protected final static String INVALID_TODO = "Oops! Please provide the description of your todo task ☹\n";
    protected final static String INVALID_EVENT = "Oops! Please provide the description of your event task ☹\n";
    protected final static String INVALID_DEADLINE = "Oops! Please provide the description of your deadline task ☹\n";

    public void showWelcome() {
        System.out.print(LINE+INTRO+LINE);
    }

    public String readCommand() {
        return input.nextLine();
    }

    public void showLine() {
        System.out.print(LINE);
    }

    public void showError(String message){
        System.out.println("Error: " + message);
    }

    public void showLoadingError() {
        System.out.println("No existing tasks. Created new list.");
    }

    public void showAdded() { System.out.println("Got it! I've added this task: "); }

    public void showCompleted() { System.out.println("Amazing! The task is marked as done now:"); }

    public void showDeleted() { System.out.println("Noted. I've removed this task: "); }

    public void showExit() { System.out.println("Bye. Hope to see you again soon!"); }

    public void printTaskCount(int listSize) { System.out.println("Now you have " + listSize + " task(s) in the list"); }

    public void printTask (String task) { System.out.println(task); }

    public void printTaskList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println(NO_TASK);
        }
        else {
            System.out.print(LINE + "Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println(i+1+". " + task.toString());
            }
            System.out.print(LINE);
        }
    }

}
