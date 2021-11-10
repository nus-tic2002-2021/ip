package duke.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import duke.task.TaskList;

/**
 * Handle all the message methods
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class Message {

    // Starting Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public static void msgGreet() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
        System.out.println("_________________________________");
    }

    // Assignment Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Display message when a task is assigned
     *
     * @param taskTypeInString String that describes the task type
     * @param isDoneInString   String that describes the isDone status
     * @param taskDetail       String that describes the task detail
     * @param numOfItem        Int that describes the number of items in taskList
     */
    public static void msgAssignTaskToDo(String taskTypeInString, String isDoneInString, String taskDetail, int numOfItem) {

        System.out.println("    Got it. I've added this task: ");
        System.out.println("      [" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail);
        System.out.println("    Now you have " + numOfItem + " tasks in the list.");
        System.out.println("_________________________________");
    }

    /**
     * Display message when a deadline task is assigned
     *
     * @param taskTypeInString String that describes the task type
     * @param isDoneInString   String that describes the isDone status
     * @param taskDetail       String that describes the task detail
     * @param dateInString     String that describes the task date
     * @param numOfItem        Int that describes the number of items in taskList
     */
    public static void msgAssignTaskDeadlineTaskDate(String taskTypeInString, String isDoneInString,
                                                     String taskDetail, String dateInString, int numOfItem) {

        System.out.println("    Got it. I've added this task: ");
        System.out.println(
                "      [" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail + "(by: " + dateInString + ")");
        System.out.println("    Now you have " + numOfItem + " tasks in the list.");
        System.out.println("_________________________________");
    }

    /**
     * Display message when a deadline task is assigned
     *
     * @param taskTypeInString String that describes the task type
     * @param isDoneInString   String that describes the isDone status
     * @param taskDetail       String that describes the task detail
     * @param dateInString     String that describes the task date
     * @param timeInString     String that describes the task time
     * @param numOfItem        Int that describes the number of items in taskList
     */
    public static void msgAssignTaskDeadlineTaskDateTaskTime(String taskTypeInString, String isDoneInString,
                                                             String taskDetail, String dateInString,
                                                             String timeInString, int numOfItem) {

        System.out.println("    Got it. I've added this task: ");
        System.out.println(
                "      [" + taskTypeInString + "][" + isDoneInString + "] "
                        + taskDetail + "(by: " + dateInString + " " + timeInString + ")");
        System.out.println("    Now you have " + numOfItem + " tasks in the list.");
        System.out.println("_________________________________");
    }

    /**
     * Display message when an event task is assigned
     *
     * @param taskTypeInString String that describes task type
     * @param isDoneInString   String that describes is done status
     * @param taskDetail       String that describes task detail
     * @param dateInString     String that describes task date
     * @param numOfItem        Int that describes the number of tasks
     */
    public static void msgAssignTaskEventTaskDate(String taskTypeInString, String isDoneInString,
                                                  String taskDetail, String dateInString, int numOfItem) {

        System.out.println("    Got it. I've added this task: ");
        System.out.println(
                "      [" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail + "(at: " + dateInString + ")");
        System.out.println("    Now you have " + numOfItem + " tasks in the list.");
        System.out.println("_________________________________");
    }

    /**
     * Display message when an event task is assigned
     *
     * @param taskTypeInString  String that describes task type
     * @param isDoneInString    String that describes is done status
     * @param taskDetail        String that describes task detail
     * @param dateInString      String that describes task date
     * @param timeStartInString String that describes the start time
     * @param numOfItem         Int that describes the number of tasks
     */
    public static void msgAssignTaskEventTaskDateTaskTimeStart(String taskTypeInString, String isDoneInString, String taskDetail,
                                                               String dateInString, String timeStartInString, int numOfItem) {

        System.out.println("    Got it. I've added this task: ");
        System.out.println("      [" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail);
        System.out.println("      (at: " + dateInString + ", from: " + timeStartInString + ")");
        System.out.println("    Now you have " + numOfItem + " tasks in the list.");
        System.out.println("_________________________________");
    }

    /**
     * Display message when an event task is assigned
     *
     * @param taskTypeInString  String that describes task type
     * @param isDoneInString    String that describes is done status
     * @param taskDetail        String that describes task detail
     * @param dateInString      String that describes task date
     * @param timeStartInString String that describes the start time
     * @param timeEndInString   String that describes the end time
     * @param numOfItem         Int that describes the number of tasks
     */
    public static void msgAssignTaskEventTaskDateTaskTimeStartTaskTimeEnd(String taskTypeInString, String isDoneInString,
                                                                          String taskDetail, String dateInString,
                                                                          String timeStartInString, String timeEndInString,
                                                                          int numOfItem) {

        System.out.println("    Got it. I've added this task: ");
        System.out.println("      [" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail);
        System.out.println("      (at: " + dateInString + ", from: " + timeStartInString + " to " + timeEndInString + ")");
        System.out.println("    Now you have " + numOfItem + " tasks in the list.");
        System.out.println("_________________________________");
    }

    // FileAccess Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Display message when task is saved
     */
    public static void msgSave() {
        System.out.println("    Progress Saved!");
        System.out.println("_________________________________");
    }

    /**
     * Display message when task is loaded
     */
    public static void msgSLoad() {
        System.out.println("    Progress loaded!");
        System.out.println("_________________________________");
    }

    // Set Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Display message to ask user which task they want to set priority
     */
    public static void msgAskUserSetTaskPriority() {
        System.out.println("    Which task do you want to set priority?");
    }

    /**
     * Display message to ask user what is the new priority
     */
    public static void msgAskUserWhatPriority() {
        System.out.println("    What is it's priority? choose 1 to 3");
        System.out.println("    1: Very urgent >>> 3: Chin Cai not that urgent");
    }

    /**
     * Display message to tell user that the new priority for a task is set successfully
     *
     * @param taskNumber           Int that describes the task number in the TaskList
     * @param taskPriorityInString String that describes the priority of the task
     */
    public static void msgSetPriority(int taskNumber, String taskPriorityInString) {
        System.out.println("    Done! The new task priority is set");
        System.out.println("    Priority of Task #" + taskNumber + " has been set to " + taskPriorityInString);
        System.out.println("_________________________________");
    }

    // Find Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Display message to tell user that the term that the user try to find is not found
     */
    public static void msgInvalidFindTerm() {
        System.out.println("    Sorry :(   Cannot find what you are looking for ~ ");
        System.out.println("_________________________________");
    }

    /**
     * Display message to show the opening message of "find"
     */
    public static void msgTaskFoundOpeningMsg() {
        System.out.println("    Here are the matching tasks in your list:");
    }

    // Other Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Display message when a task is marked as done
     *
     * @param myList     TaskList that contains the list of task
     * @param taskNumber Int that represents the task number
     */
    public static void msgMarkDone(TaskList myList, int taskNumber) {
        System.out.println("    Naisuuuu! This task is marked as done: ");
        msgBlankBeforeTaskDetail();
        new MsgTaskDetail(myList, taskNumber).showTaskDetail();
        System.out.println("_________________________________");
    }

    /**
     * Display [x] where x is the int parameter
     * <p>
     * Used in CmdList function to show the following:
     * 1.
     * 2.
     * 3.
     * etc.
     *
     * @param index Int that will be put inside the bracket
     */
    public static void msgShowBracketWithIndex(int index) {
        System.out.print("    " + index + ".");
    }

    /**
     * Display blanks
     */
    public static void msgBlankBeforeTaskDetail() {
        System.out.print("    ");
    }

    /**
     * Display dash lines separator
     */
    public static void msgDashLines() {
        System.out.println("_________________________________");
    }

    public static void msgTaskTodo(String taskTypeInString, String isDoneInString,
                                   String taskDetail, String taskPriorityInString) {
        System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail + " [" + taskPriorityInString + "]");
    }

    public static void msgTaskEvent(String taskTypeInString, String isDoneInString, String taskDetail,
                                    String year, String month, String day, String taskPriorityInString) {
        System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail + " [" + taskPriorityInString + "]");
        System.out.println("             (at " + year + " " + month + " " + day + ")");
    }

    public static void msgTaskEvent(String taskTypeInString, String isDoneInString, String taskDetail,
                                    String year, String month, String day, String timeStart, String taskPriorityInString) {
        System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail + " [" + taskPriorityInString + "]");
        System.out.print("             (at " + year + " " + month + " " + day);
        System.out.println(" ; from: " + timeStart + ")");
    }

    public static void msgTaskEvent(String taskTypeInString, String isDoneInString, String taskDetail,
                                    String year, String month, String day, String timeStart,
                                    String timeEnd, String taskPriorityInString) {
        System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail + " [" + taskPriorityInString + "]");
        System.out.print("             (at " + year + " " + month + " " + day);
        System.out.println(" ; from: " + timeStart + " to " + timeEnd + ")");
    }

    public static void msgTaskDeadline(String taskTypeInString, String isDoneInString, String taskDetail,
                                       String year, String month, String day, String taskPriorityInString) {
        System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail + " [" + taskPriorityInString + "]");
        System.out.println("             by: " + year + " " + month + " " + day + ")");
    }

    public static void msgTaskDeadline(String taskTypeInString, String isDoneInString, String taskDetail,
                                       String year, String month, String day,
                                       String timeStart, String taskPriorityInString) {
        System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail + " [" + taskPriorityInString + "]");
        System.out.println("             by: " + year + " " + month + " " + day + " " + timeStart + ")");
    }


    /**
     * Display message when a task is removed
     */
    public static void msgRemoveItem(TaskList myList, int taskNumber, int numOfTaskAfterDelete) {

        System.out.println("    Noted. I've removed this task:");
        msgBlankBeforeTaskDetail();
        new MsgTaskDetail(myList, taskNumber).showTaskDetail();
        System.out.println("    Now you have " + numOfTaskAfterDelete + " tasks in the list.");
        System.out.println("_________________________________");
    }

    public static void msgArrowHead(){
        System.out.print(">> ");
    }
    // Error Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Display message when a task is marked as done
     *
     * @param e Exception error
     */
    public static void msgError(Exception e) {
        System.out.println("Error Occurs: " + e);
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     */
    public static void msgInvalidInput() {
        System.out.println("    Sorry :(   Invalid Input. Try Again ~ ");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Description of the task is missing
     */
    public static void msgInvalidInputMissingDescription() {
        System.out.println("    ☹  OOPS!!! The description cannot be empty.");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Date of the task is missing
     */
    public static void msgInvalidInputMissingDay() {
        System.out.println("    ☹  OOPS!!! The day cannot be empty.");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Time of the task is missing
     */
    public static void msgInvalidInputMissingTime() {
        System.out.println("    ☹  OOPS!!! The time cannot be empty.");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * The task number is not found
     */
    public static void msgInvalidTaskNumber() {
        System.out.println("    ☹  OOPS!!! The task number is invalid.");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * The priority is invalid
     */
    public static void msgInvalidPriority() {
        System.out.println("    ☹  OOPS!!! The priority is invalid. Choose 1, 2 or 3");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Format of the date is unaccepted
     */
    public static void msgInvalidInputWrongDateTimeFormat() {
        System.out.println("    ☹  OOPS!!! Please follow this format:");
        System.out.println("    /by yyyy-mm-dd hh:mm");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Format of the date and time is unaccepted
     */
    public static void msgInvalidInputWrongDateTimeStartEndFormat() {
        System.out.println("    ☹  OOPS!!! Please follow this format:");
        System.out.println("    /at yyyy-mm-dd hh:mm hh:mm:");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Start time should be before End time
     */
    public static void msgInvalidInputTimeStartLaterThanTimeEnd() {
        System.out.println("    ☹  OOPS!!! Event start time cannot be later than end time!");
        System.out.println("_________________________________");
    }

    // Ending Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Display message before the end of program
     */
    public static void msgBye() throws IOException {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_________________________________");
        msgBuddhaProtectMe();
    }

    /**
     * Display Buddah.txt
     */
    private static void msgBuddhaProtectMe() throws IOException {
        String everything = "";

        // ... \Duke\src\main\resources\buddha.txt
        // File.separator == " \ "

        String pathRoot = System.getProperty("user.dir");
        // pathRoot = D:\My Files\School Documents\Repository\Duke

        String pathRssFolder = "src" + File.separator + "resources";
        // pathRssFolder = src\main\resources

        String pathFileName = "buddha.txt";
        // pathFileName = buddha.txt

        String filePath = pathRoot + File.separator + pathRssFolder + File.separator + pathFileName;
        // filePath = D:\My Files\School
        // Documents\Repository\Duke\src\main\resources\buddha.txt

        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("\n >>> Buddah Protection is not in forced <<<\n");
            System.out.println(e);
        } finally {
            System.out.println(everything);
        }
    }
}
