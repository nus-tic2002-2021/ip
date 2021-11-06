package ui;

import exception.DukeException;
import tasklist.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

public class Ui {

    private static final String SEPARATOR =
            "___________________________________________________________________________";

    private static final Scanner in = new Scanner(System.in);

    public String userInput(){
        return in.nextLine();
    }

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

    public static void howToUse(){
        System.out.println("How to interact with Duke:\n");
        System.out.println("\tFunction:                          Command:");
        System.out.println("\tView all existing tasks:           list");
        System.out.println("\tAdd a new to-do:                   todo {description}");
        System.out.println("\tAdd a new deadline:                deadline {description} /by {d/M/yyyy HHmm}");
        System.out.println("\tAdd a new event:                   event {description} /at {d/M/yyyy HHmm}");
        System.out.println("\tMark a task as completed [√]:      done {taskId}");
        System.out.println("\tDelete a task:                     delete {taskId}");
        System.out.println("\tSearch tasks by keyword:           search {keyword}");
        System.out.println("\tCheck schedule of a date:          view {yyyy-MM-dd}");
        System.out.println("\tShow help message:                 help");
        System.out.println("\tDelete all tasks:                  reset");
        System.out.println("\tExit the program:                  bye");
    }

    public static void goodBye (){
        System.out.println("                                                   ,:\n" +
            "                                                 ,' |\n" +
            "                                                /   :\n" +
            "                                             --'   /\n" +
            "                                             \\/ />/\n" +
            "                                             / /_\\\n" +
            "                                          __/   /\n" +
            "                                          )'-. /\n" +
            "                                          ./  :\\\n" +
            "                                           /.' '\n" +
            "                                         '/'\n" +
            "                                         +\n" +
            "                                        '\n" +
            "                                      `.\n" +
            "                                  .-\"-\n" +
            "                                 (    |\n" +
            "                              . .-'  '.\n" +
            "                             ( (.   )8:\n" +
            "                         .'    / (_  )\n" +
            "                          _. :(.   )8P  `\n" +
            "                      .  (  `-' (  `.   .\n" +
            "                       .  :  (   .a8a)\n" +
            "                      /_`( \"a `a. )\"'\n" +
            "                  (  (/  .  ' )=='\n" +
            "                 (   (    )  .8\"   +\n" +
            "                   (`'8a.( _(   (\n" +
            "                ..-. `8P    ) `  )  +\n" +
            "              -'   (      -ab:  )\n" +
            "            '    _  `    (8P\"Ya\n" +
            "          _(    (    )b  -`.  ) +\n" +
            "         ( 8)  ( _.aP\" _a   \\( \\   *\n" +
            "       +  )/    (8P   (88    )  )\n" +
            "          (a:f   \"     `\"       `\n" +
            "\nBye, i will go back to space.");
    }

    public void errorMessage (Exception e) {
        System.out.println(e.getMessage());
    }

    public void exceptionMessage (Exception e) {
        System.err.println(e.getMessage());
    }

    public static void validateEventCommand(String[] command) {
        if (command.length < 2 || command[1].equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty. Please re-enter:");
        } else if (!command[1].contains("/at")) {
            throw new DukeException("☹ OOPS!!! The date of a event cannot be empty. Please re-enter:");
        }
    }

    public static void validateDeadlineCommand(String[] command) {
        if (command.length < 2 || command[1].equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty. Please re-enter:");
        } else if (!command[1].contains("/by")) {
            throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty. Please re-enter:");
        }
    }

    public static void validateTodoCommand(String[] command) {
        if (command.length < 2 || command[1].equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty. Please re-enter:");
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
            throw new DukeException("☹ Please state task number.");
        }
        int taskNumber  = Integer.parseInt(command[1]) - 1;
        if (taskNumber >= taskList.size()) {
            throw new DukeException("☹ There is no such task.");
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
                "Please enter datetime in the format of 'dd/MM/yyyy HHmm'";
    }

    public static void validateViewCommand(String[] command){
        if (command.length < 2) {
            throw new DukeException("☹ OOPS!!! The date cannot be empty. Please re-enter:");
        }
    }

    public static void validateSearchCommand(String[] command){
        if (command.length < 2 || command[1].equals("")) {
            throw new DukeException("☹ OOPS!!! Please specify keyword:");
        }
    }

    public void printTaskByKeyword(TaskList taskList, String keyword){
        System.out.println("You have total " + (taskList.size()) +" tasks related to <" + keyword +">.");
    }

    public void reset(){
        System.out.println("Your task list has been reset. Please add new task.");
    }

    public static boolean validateResetCommand(){
        boolean b = false;
        System.out.println("Heads up! You are about to reset your task list. Type \"confirm\" to continue.");
        String confirm = in.nextLine();
        if (confirm.equalsIgnoreCase("confirm")){
            b = true;
        }
        return b;
    }
}