package duke.ui;

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

    /**
     * Display greet message at the start
     */
    public static void msgGreet() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(">>> Copyright (c) Teng Kang Teng (A0211547L NUS) <<<");
        System.out.println(">>> Version 0.2 <<<\n");
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
        System.out.println("Enter \"Info\" to show all Duke commands. ");
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
    public static void msgAssignTaskToDo(String taskTypeInString, String isDoneInString,
                                         String taskDetail, int numOfItem) {

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
                "      [" + taskTypeInString + "][" + isDoneInString + "] "
                        + taskDetail + "(by: " + dateInString + ")");
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
        System.out.println("      [" + taskTypeInString + "]["
                + isDoneInString + "] "
                + taskDetail + "(at: " + dateInString + ")");
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
    public static void msgAssignTaskEventTaskDateTaskTimeStart(String taskTypeInString,
                                                               String isDoneInString,
                                                               String taskDetail,
                                                               String dateInString,
                                                               String timeStartInString,
                                                               int numOfItem) {

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
    public static void msgAssignEventTaskDateTimeStartEnd(String taskTypeInString,
                                                       String isDoneInString,
                                                       String taskDetail,
                                                       String dateInString,
                                                       String timeStartInString,
                                                       String timeEndInString,
                                                       int numOfItem) {

        System.out.println("    Got it. I've added this task: ");
        System.out.println("      [" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail);
        System.out.println("      (at: " + dateInString + ", from: " + timeStartInString
                + " to " + timeEndInString + ")");
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
     * Display message when a task is marked as not done
     *
     * @param myList     TaskList that contains the list of task
     * @param taskNumber Int that represents the task number
     */
    public static void msgMarkUnDone(TaskList myList, int taskNumber) {
        System.out.println("    Okie! This task is marked as not done: ");
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

    /**
     * Display TODO task description
     */
    public static void msgTaskTodo(String taskTypeInString, String isDoneInString,
                                   String taskDetail, String taskPriorityInString) {
        System.out.println("[" + taskTypeInString + "]["
                + isDoneInString + "] " + taskDetail + " [" + taskPriorityInString + "]");
    }

    /**
     * Display EVENT task description
     */
    public static void msgTaskEvent(String taskTypeInString, String isDoneInString, String taskDetail,
                                    String year, String month, String day, String taskPriorityInString) {
        System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] "
                + taskDetail + " [" + taskPriorityInString + "]");
        System.out.println("             (at " + year + " " + month + " " + day + ")");
    }

    /**
     * Display EVENT task description
     */
    public static void msgTaskEvent(String taskTypeInString,
                                    String isDoneInString,
                                    String taskDetail,
                                    String year,
                                    String month,
                                    String day,
                                    String timeStart,
                                    String taskPriorityInString) {
        System.out.println("[" + taskTypeInString + "]["
                + isDoneInString + "] " + taskDetail + " [" + taskPriorityInString + "]");
        System.out.print("             (at " + year + " " + month + " " + day);
        System.out.println(" ; from: " + timeStart + ")");
    }

    /**
     * Display EVENT task description
     */
    public static void msgTaskEvent(String taskTypeInString, String isDoneInString, String taskDetail,
                                    String year, String month, String day, String timeStart,
                                    String timeEnd, String taskPriorityInString) {
        System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] "
                + taskDetail + " [" + taskPriorityInString + "]");
        System.out.print("             (at " + year + " " + month + " " + day);
        System.out.println(" ; from: " + timeStart + " to " + timeEnd + ")");
    }

    /**
     * Display DEADLINE task description
     */
    public static void msgTaskDeadline(String taskTypeInString, String isDoneInString, String taskDetail,
                                       String year, String month, String day, String taskPriorityInString) {
        System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] "
                + taskDetail + " [" + taskPriorityInString + "]");
        System.out.println("             by: " + year + " " + month + " " + day + ")");
    }

    /**
     * Display DEADLINE task description
     */
    public static void msgTaskDeadline(String taskTypeInString, String isDoneInString, String taskDetail,
                                       String year, String month, String day,
                                       String timeStart, String taskPriorityInString) {
        System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] "
                + taskDetail + " [" + taskPriorityInString + "]");
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

    /**
     * Display arrow head
     */
    public static void msgArrowHead() {
        System.out.print(">> ");
    }

    /**
     * Display full list of Duke command
     */
    public static void msgShowDukeCommandList() {
        System.out.println(">> Full List of Duke Command Available:");
        System.out.println("    1. todo (task description)");
        System.out.println("        >> add a TODO task");
        System.out.println("    2. event (task description)");
        System.out.println("        >> add a EVENT task");
        System.out.println("    3. event (task description)/at (date)");
        System.out.println("        >> add a EVENT task");
        System.out.println("    4. event (task description)/at (date) (time)");
        System.out.println("        >> add a EVENT task");
        System.out.println("    5. deadline (task description)");
        System.out.println("        >> add a DEADLINE task");
        System.out.println("    6. deadline (task description)/by (date)");
        System.out.println("        >> add a DEADLINE task");
        System.out.println("    7. deadline (task description)/by (date) (time start) (time end)");
        System.out.println("        >> add a DEADLINE task");
        System.out.println("    8. list");
        System.out.println("        >> show full list of task");
        System.out.println("    9. set");
        System.out.println("        >> change priority of a task");
        System.out.println("    10. done (task number)");
        System.out.println("        >> mark a task as completed");
        System.out.println("    11. find (keyword)");
        System.out.println("        >> find a task based on keyword");
        System.out.println("    12. info");
        System.out.println("        >> display full list of Duke Command");
        System.out.println("    13. delete (task number)");
        System.out.println("        >> delete a task");
        System.out.println("    14. save");
        System.out.println("        >> save all the tasks");
        System.out.println("    15. bye");
        System.out.println("        >> end your friendly Duke chat box");
        System.out.println("_________________________________");
    }
    // Error Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Display message when a progress file fail to load
     */
    public static void msgUnableToLoadProgress() {
        System.out.println(" >> Fail to load progress. New Task List is created <<");
        System.out.println("_________________________________");
    }


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
        System.out.println("    OOPS!!! The description cannot be empty.");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Date of the task is missing
     */
    public static void msgInvalidInputMissingDate() {
        System.out.println("    OOPS!!! The date cannot be empty.");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Time of the task is missing
     */
    public static void msgInvalidInputMissingTime() {
        System.out.println("    OOPS!!! The time cannot be empty.");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * The task number is not found
     */
    public static void msgInvalidTaskNumber() {
        System.out.println("    OOPS!!! The task number is invalid.");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * The priority is invalid
     */
    public static void msgInvalidPriority() {
        System.out.println("    OOPS!!! The priority is invalid. Choose 1, 2 or 3");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Format of the date is unaccepted
     */
    public static void msgInvalidInputWrongDateTimeFormat() {
        System.out.println("    OOPS!!! Please follow this format:");
        System.out.println("    /by yyyy-mm-dd hh:mm");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Format of the date and time is unaccepted
     */
    public static void msgInvalidInputWrongDateTimeStartEndFormat() {
        System.out.println("    OOPS!!! Please follow this format:");
        System.out.println("    /at yyyy-mm-dd hh:mm hh:mm:");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Start time should be before End time
     */
    public static void msgInvalidInputTimeStartLaterThanTimeEnd() {
        System.out.println("    OOPS!!! Event start time cannot be later than end time!");
        System.out.println("_________________________________");
    }

    /**
     * Display message about task list being empty
     */
    public static void msgTaskListIsEmpty() {
        System.out.println("    OOPS!!! The task list is empty.");
        System.out.println("_________________________________");
    }

    /**
     * Display message when buddha.txt fails to load
     */
    public static void msgUnableToLoadBuddha() {
        System.out.println(" >> Buddha Protection is under maintenance <<");
        System.out.println("_________________________________");
    }

    // Ending Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Display message before the end of program
     */
    public static void msgBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_________________________________");
    }

    /**
     * Display buddha.txt before the end of program
     */
    public static void msgBuddha(String buddhaText) {
        System.out.println(buddhaText);
    }
}
