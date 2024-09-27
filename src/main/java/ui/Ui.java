package ui;

import exception.DukeException;
import tasklist.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

/**
 * Ui Class contains all the interacting messages to be displayed on screen
 *
 */
public class Ui {

    private static final String SEPARATOR = "___________________________________________________________________________";

    private static final Scanner in = new Scanner(System.in);

    /**
     * The method reads the user input and convert to string
     * @return String user input
     */
    public String userInput(){
        return in.nextLine();
    }

    /**
     * Welcome message template on start up
     */
    public static void Welcome (){
        System.out.println("        _..._\n" +
                "      .'     '.      _\n" +
                "     /    .-\"\"-\\   _/ \\\n" +
                "   .-|   /:.   |  |   |\n" +
                "   |  \\  |:.   /.-'-./\n" +
                "   | .-'-;:__.'    =/\n" +
                "   .'=  *=|DUKE _.='\n" +
                "  /   _.  |    ;\n" +
                " ;-.-'|    \\   |\n" +
                "/   | \\    _\\  _\\\n" +
                "\\__/'._;.  ==' ==\\\n" +
                "         \\    \\   |\n" +
                "         /    /   /\n" +
                "         /-._/-._/\n" +
                "         \\   `\\  \\" + "\t\tAye there. I'm DUKE.\n" +
                "          `-._/._/" + "\t\tHow can i help you?");
        System.out.println("___^/\\___^--____/\\____O______________/\\/\\---/\\___________---______________ \n" +
                "   /\\^   ^  ^    ^                  ^^ ^  '\\ ^          ^       ---         ");
        howToUse();
        System.out.println(SEPARATOR);
    }

    /**
     * Help message template
     */
    public static void howToUse(){
        System.out.println("How to interact with me: \n");
        System.out.println("Command:        Function:");
        System.out.println("list            View all existing tasks");
        System.out.println("todo            Add a new to-do");
        System.out.println("deadline        Add a new deadline");
        System.out.println("event           Add a new event");
        System.out.println("done            Mark a task as completed [√]");
        System.out.println("delete          Delete a task");
        System.out.println("search          Search tasks by keyword");
        System.out.println("view            Check schedule of a date");
        System.out.println("help            Show help message");
        System.out.println("reset           Delete all tasks");
        System.out.println("bye             Exit the program");
    }

    /**
     * End message template
     */
    public static void goodBye (){
        System.out.println("Bye. Hope to see you again soon.");
    }

    public void errorMessage (Exception e) {
        System.out.println(e.getMessage());
    }

    public void exceptionMessage (Exception e) {
        System.err.println(e.getMessage());
    }

    public static void validateEventCommand(String[] command) {
        if (command.length < 2 || command[1].equals("")) {
            throw new DukeException("OOPS! The description of a event cannot be empty. Please re-enter:");
        } else if (!command[1].contains("/at")) {
            throw new DukeException("OOPS! The date of a event cannot be empty. Please re-enter:");
        }
    }

    public static void validateDeadlineCommand(String[] command) {
        if (command.length < 2 || command[1].equals("")) {
            throw new DukeException("OOPS! The description of a deadline cannot be empty. Please re-enter:");
        } else if (!command[1].contains("/by")) {
            throw new DukeException("OOPS! The date of a deadline cannot be empty. Please re-enter:");
        }
    }

    public static void validateTodoCommand(String[] command) {
        if (command.length < 2 || command[1].equals("")) {
            throw new DukeException("OOPS! The description of a todo cannot be empty. Please re-enter:");
        }
    }

    public void Separator() {System.out.println(SEPARATOR);}

    public void printAddCommand(TaskList taskList){
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t"+taskList.get(taskList.size()-1).printTask());
    }

    public void printTaskList(TaskList taskList) throws NullPointerException{
        if (taskList.size() == 0) {
            System.out.println("List is empty. Please add new task.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + taskList.get(i).printTask());
            }
        }
    }


    public static void validateDoneCommand(String[] command, TaskList taskList){
        if (command.length < 2 || command[1].equals("")) {
            throw new DukeException("Please state task number.");
        }
        int taskNumber  = Integer.parseInt(command[1]) - 1;
        if (taskNumber >= taskList.size() || taskNumber < 0 ) {
            throw new DukeException("The task number is not valid.");
        }
    }

    public void printDone(int i, TaskList taskList){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t"+taskList.get(i).printTask());
    }

    public void printDelete(int i, TaskList taskList){
        System.out.println("Noted. I've removed this task: ");
        System.out.println("\t"+taskList.get(i).printTask());
    }

    public void printTaskCount(TaskList taskList){
        System.out.println("Now you have total " + (taskList.size()) +" tasks in the list.");
    }

    public void printTaskListStr(TaskList taskList) {
        System.out.println("You have total " + (taskList.size()) + " tasks in the list.");
    }

    public void printTaskOfDate(TaskList taskList, LocalDate date){
        System.out.println("You have total " + (taskList.size()) +" tasks on " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))+".");
    }

    public static String validateDateTime(){
        return "Task cannot be added. \n" +
                "Please enter datetime in the format of 'd/M/yyyy HHmm'";
    }

    public static void validateViewCommand(String[] command){
        if (command.length < 2) {
            throw new DukeException("OOPS! The date cannot be empty. Please re-enter:");
        }
    }

    public static void validateSearchCommand(String[] command){
        if (command.length < 2 || command[1].equals("")) {
            throw new DukeException("OOPS! Please specify keyword:");
        }
    }

    public void printTaskByKeyword(TaskList taskList, String keyword){
        System.out.println("You have total " + (taskList.size()) +" tasks related to <" + keyword +">.");
    }

    public void printReset(){
        System.out.println("Your task list has been reset. Please add new task.");
    }

    public void printNotReset(){System.out.println("No worries, your task list remain unchanged.");}

    public boolean validateResetCommand(){
        boolean b = false;
        System.out.println("Heads up! You are about to reset your task list. Type \"confirm\" to continue.");
        String confirm = in.nextLine();
        if (confirm.equalsIgnoreCase("confirm")){
            b = true;
        }
        return b;
    }
}