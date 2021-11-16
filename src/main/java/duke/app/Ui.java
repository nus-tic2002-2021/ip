package duke.app;

import java.util.Scanner;

public class Ui {

    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printAddTask(boolean success, String newTask) {
        if (!success) {
            System.out.println("The same task has been added previously, please enter another task");
        }
        System.out.println("Got it. I've added this task: " + " " + newTask);
    }

    public void printTotalNumber(int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void printDeleteTask(String removedTask) {
        System.out.println("Noted. I've removed this task: \n" + removedTask);
    }

    public void printIndexWrongMessage() {
        System.out.println("Oops the index is wrong, please enter a valid index");
    }

    public void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "Hey this is Blanc, welcome to your universe\n"
                + "I will provide everything you need:))\n";
        String instruction = "Please follow the instructions below\n"
                + "1. \"list\": print a list of tasks\n"
                + "2. Mark the task as done using \"done index\" (e.g. done 2)\n"
                + "3. add task using \"todo\" \"deadline\" \"event\" task description /by or /at datetime \n"
                + "   Note: for datetime (necessary for deadline and event), follow the format yyyy-MM-dd HH:mm\n"
                + "   e.g. -> todo reading book;\n"
                + "        -> deadline homework submission /by 2021-11-17 18:00\n"
                + "        -> event project meeting /at 2021-11-22 12:00\n"
                + "4. Delete task using \"delete index\" (e.g. delete 2): delete the 2nd task on the list\n"
                + "5. Find a list of task using a keyword, \"find keyword\" (e.g. find book)\n"
                + "6. Remind the list of tasks due in a specified number of days, \"remind number\" (e.g. remind 7)\n"
                + "7. \"bye\": end the program\n"
                +"Please type any command to start: \n";
        System.out.println(greeting);
        System.out.println(instruction);
    }

    public void printTask() {
        System.out.println("Here are the tasks in your list:");
    }

    public void printDoneMessage(String taskDone) {
        System.out.println("Nice! I've marked this task as done: \n" + taskDone);
    }

    public void showLine() {
        System.out.println("__________________________________");
    }

    public void printExitMessage() {
        System.out.println("Bye. Have a nice day!");
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    public void showRemindCommandMessage() {
        System.out.println("Here is the list of the tasks due soon: ");
    }

    public void printFindCommand() {
        System.out.println("Here are the matching tasks in your list:");
    }
}
