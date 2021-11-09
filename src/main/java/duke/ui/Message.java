package duke.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.TaskList;
import duke.task.TaskPriority;
import duke.task.TaskType;

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
     * @param myList     TaskList that contains the list of task
     * @param taskNumber Int that represents the task number
     */
    public void msgAssignTask(TaskList myList, int taskNumber) {

        TaskType taskType = myList.getTaskType(taskNumber);
        String taskTypeInString = TaskType.taskTypeToString(taskType);

        boolean isDone = myList.getTaskDoneStatus(taskNumber);
        String isDoneInString = (isDone ? "X" : " ");

        String taskDetail = myList.getTaskDetail(taskNumber);

        System.out.println("    Got it. I've added this task: ");
        System.out.println("      [" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail);
        System.out.println("    Now you have " + myList.getNumOfItem() + " tasks in the list.");
        System.out.println("_________________________________");
    }

    /**
     * Display message when a deadline task is assigned
     *
     * @param myList     TaskList that contains the list of task
     * @param taskNumber Int that represents the task number
     */
    public void msgAssignTaskDeadlineTaskDate(TaskList myList, int taskNumber) {

        TaskType taskType = myList.getTaskType(taskNumber);
        String taskTypeInString = TaskType.taskTypeToString(taskType);

        boolean isDone = myList.getTaskDoneStatus(taskNumber);
        String isDoneInString = (isDone ? "X" : " ");

        String taskDetail = myList.getTaskDetail(taskNumber);

        String dateString = myList.getTaskDeadLineTaskDateInString(taskNumber);

        System.out.println("    Got it. I've added this task: ");
        System.out.println(
                "      [" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail + "(by: " + dateString + ")");
        System.out.println("    Now you have " + myList.getNumOfItem() + " tasks in the list.");
        System.out.println("_________________________________");
    }

    /**
     * Display message when a deadline task is assigned
     *
     * @param myList     TaskList that contains the list of task
     * @param taskNumber Int that represents the task number
     */
    public void msgAssignTaskDeadlineTaskDateTaskTime(TaskList myList, int taskNumber) {

        TaskType taskType = myList.getTaskType(taskNumber);
        String taskTypeInString = TaskType.taskTypeToString(taskType);

        boolean isDone = myList.getTaskDoneStatus(taskNumber);
        String isDoneInString = (isDone ? "X" : " ");

        String taskDetail = myList.getTaskDetail(taskNumber);

        String dateString = myList.getTaskDeadLineTaskDateInString(taskNumber);
        String timeString = myList.getTaskDeadLineTaskTimeInString(taskNumber);

        System.out.println("    Got it. I've added this task: ");
        System.out.println(
                "      [" + taskTypeInString + "][" + isDoneInString + "] "
                        + taskDetail + "(by: " + dateString + " " + timeString + ")");
        System.out.println("    Now you have " + myList.getNumOfItem() + " tasks in the list.");
        System.out.println("_________________________________");
    }

    /**
     * Display message when an event task is assigned
     *
     * @param myList     TaskList that contains the list of task
     * @param taskNumber Int that represents the task number
     */
    public void msgAssignTaskEventTaskDate(TaskList myList, int taskNumber) {

        TaskType taskType = myList.getTaskType(taskNumber);
        String taskTypeInString = TaskType.taskTypeToString(taskType);

        boolean isDone = myList.getTaskDoneStatus(taskNumber);
        String isDoneInString = (isDone ? "X" : " ");

        String taskDetail = myList.getTaskDetail(taskNumber);

        String dateString = myList.getTaskEventTaskDateInString(taskNumber);

        System.out.println("    Got it. I've added this task: ");
        System.out.println(
                "      [" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail + "(at: " + dateString + ")");
        System.out.println("    Now you have " + myList.getNumOfItem() + " tasks in the list.");
        System.out.println("_________________________________");
    }

    /**
     * Display message when an event task is assigned
     *
     * @param myList     TaskList that contains the list of task
     * @param taskNumber Int that represents the task number
     */
    public void msgAssignTaskEventTaskDateTaskTimeStart(TaskList myList, int taskNumber) {

        TaskType taskType = myList.getTaskType(taskNumber);
        String taskTypeInString = TaskType.taskTypeToString(taskType);

        boolean isDone = myList.getTaskDoneStatus(taskNumber);
        String isDoneInString = (isDone ? "X" : " ");

        String taskDetail = myList.getTaskDetail(taskNumber);

        String dateString = myList.getTaskEventTaskDateInString(taskNumber);
        String timeStartString = myList.getTaskEventTaskTimeStartInString(taskNumber);

        System.out.println("    Got it. I've added this task: ");
        System.out.println("      [" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail);
        System.out.println("      (at: " + dateString + ", from: " + timeStartString + ")");
        System.out.println("    Now you have " + myList.getNumOfItem() + " tasks in the list.");
        System.out.println("_________________________________");
    }

    /**
     * Display message when an event task is assigned
     *
     * @param myList     TaskList that contains the list of task
     * @param taskNumber Int that represents the task number
     */
    public void msgAssignTaskEventTaskDateTaskTimeStartTaskTimeEnd(TaskList myList, int taskNumber) {

        TaskType taskType = myList.getTaskType(taskNumber);
        String taskTypeInString = TaskType.taskTypeToString(taskType);

        boolean isDone = myList.getTaskDoneStatus(taskNumber);
        String isDoneInString = (isDone ? "X" : " ");

        String taskDetail = myList.getTaskDetail(taskNumber);

        String dateString = myList.getTaskEventTaskDateInString(taskNumber);
        String timeStartString = myList.getTaskEventTaskTimeStartInString(taskNumber);
        String timeEndString = myList.getTaskEventTaskTimeEndInString(taskNumber);

        System.out.println("    Got it. I've added this task: ");
        System.out.println("      [" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail);
        System.out.println("      (at: " + dateString + ", from: " + timeStartString + " to " + timeEndString + ")");
        System.out.println("    Now you have " + myList.getNumOfItem() + " tasks in the list.");
        System.out.println("_________________________________");
    }

    // FileAccess Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Display message when task is saved
     */
    public void msgSave() {
        System.out.println("    Progress Saved!");
        System.out.println("_________________________________");
    }

    /**
     * Display message when task is loaded
     */
    public void msgSLoad() {
        System.out.println("    Progress loaded!");
        System.out.println("_________________________________");
    }

    // Set Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Display message to ask user which task they want to set priority
     */
    public void msgAskUserSetTaskPriority() {
        System.out.println("    Which task do you want to set priority?");
    }

    /**
     * Display message to ask user what is the new priority
     */
    public void msgAskUserWhatPriority() {
        System.out.println("    What is it's priority? choose 1 to 3");
        System.out.println("    1: Very urgent >>> 3: Chin Cai not that urgent");
    }

    /**
     * Display message to tell user that the new priority for a task is set successfully
     */
    public void msgSetPriority(TaskList myList, int taskNumber) {
        System.out.println("    Done! The new task priority is set");
        TaskPriority taskPriority = myList.getTaskPriority(taskNumber);
        String taskPriorityInString = TaskPriority.convertPriorityToString(taskPriority);
        System.out.println("    Priority of Task #" + taskNumber + 1 + " has been set to " + taskPriorityInString);
        System.out.println("_________________________________");
    }

    // Find Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Display message to tell user that the term that the user try to find is not found
     */
    public void msgInvalidFindTerm() {
        System.out.println("    Sorry :(   Cannot find what you are looking for ~ ");
        System.out.println("_________________________________");
    }

    /**
     * Display message to show a list of task found based on the term
     */
    public void msgTaskFound(TaskList myList, ArrayList<Integer> taskNumberContainingSearchTerm) {
        System.out.println("    Here are the matching tasks in your list:");
        int index = 1;
        for (int i = 0; i < taskNumberContainingSearchTerm.size(); i++) {
            int taskNumber = taskNumberContainingSearchTerm.get(i);
            msgBlankBeforeTaskDetail();
            System.out.print(index + ".");
            msgTaskDetail(myList, taskNumber);
            index++;
        }
    }

    // Other Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Display message when a task is marked as done
     *
     * @param myList     TaskList that contains the list of task
     * @param taskNumber Int that represents the task number
     */
    public void msgMarkDone(TaskList myList, int taskNumber) {
        System.out.println("    Naisuuuu! This task is marked as done: ");
        msgBlankBeforeTaskDetail();
        msgTaskDetail(myList, taskNumber);
        System.out.println("_________________________________");
    }

    /**
     * Display message to show the full list of task
     *
     * @param myList TaskList that contains the list of task
     */
    public void msgList(TaskList myList) {
        for (int i = 0; i < myList.getNumOfItem(); i++) {
            System.out.print("    " + Integer.toString(i + 1) + ".");
            msgTaskDetail(myList, i);
        }
        System.out.println("_________________________________");
    }

    /**
     * Display blanks
     */
    public void msgBlankBeforeTaskDetail() {
        System.out.print("    ");
    }

    /**
     * Display dash lines separator
     */
    public void msgDashLines() {
        System.out.println("_________________________________");
    }

    /**
     * Display detail of a task
     *
     * @param myList     TaskList that contains the list of task
     * @param taskNumber Int that represents the task number
     */
    public void msgTaskDetail(TaskList myList, int taskNumber) {
        TaskType taskType = myList.getTaskType(taskNumber);
        String taskTypeInString = TaskType.taskTypeToString(taskType);

        boolean isDone = myList.getTaskDoneStatus(taskNumber);
        String isDoneInString = (isDone ? "X" : " ");

        String taskDetail = myList.getTaskDetail(taskNumber);

        String dateTimeString = "";
        String year = "";
        String month = "";
        String day = "";
        String time = "";
        String timeStart = "";
        String timeEnd = "";

        switch (taskType) {
        case TODOS:
            System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail);
            break;
        case DEADLINE:
            // dateTimeString = myList.getTaskDeadLine_TaskDate_InString(taskNumber);
            year = myList.getTaskDeadLineTaskDateYearInString(taskNumber);
            month = myList.getTaskDeadLineTaskDateMonthInString(taskNumber);
            day = myList.getTaskDeadLineTaskDateDayInString(taskNumber);
            time = myList.getTaskDeadLineTaskTimeInString(taskNumber);

            if (time.equals("null")) {
                System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail
                        + "(by: " + year + " " + month + " " + day + ")");
            } else {
                System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail
                        + "(by: " + year + " " + month + " " + day + " " + time + ")");
            }
            break;
        case EVENT:
            // dateTimeString = myList.getTaskDeadLine_TaskDate_InString(taskNumber);
            year = myList.getTaskEventTaskDateYearInString(taskNumber);
            month = myList.getTaskEventTaskDateMonthInString(taskNumber);
            day = myList.getTaskEventTaskDateDayInString(taskNumber);
            timeStart = myList.getTaskEventTaskTimeStartInString(taskNumber);
            timeEnd = myList.getTaskEventTaskTimeEndInString(taskNumber);

            if (timeStart.equals("null") && timeEnd.equals("null")) {
                System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail
                        + "(at: " + year + " " + month + " " + day + ")");
            } else if (timeEnd.equals("null")) {
                System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail);
                System.out.print("             (at " + year + " " + month + " " + day);
                System.out.println(" ; from: " + timeStart + ")");
            } else {
                System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail);
                System.out.print("             (at " + year + " " + month + " " + day);
                System.out.println(" ; from: " + timeStart + " to " + timeEnd + ")");
            }
            break;
        default:
            break;
        }
    }

    /**
     * Display message when a task is removed
     */
    public void msgRemoveItem(TaskList myList, int taskNumber) {

        System.out.println("    Noted. I've removed this task:");
        msgBlankBeforeTaskDetail();
        msgTaskDetail(myList, taskNumber);
        System.out.println("    Now you have " + myList.getNumOfItem() + " tasks in the list.");
        System.out.println("_________________________________");
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
    public void msgInvalidInput() {
        System.out.println("    Sorry :(   Invalid Input. Try Again ~ ");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Description of the task is missing
     */
    public void msgInvalidInputMissingDescription() {
        System.out.println("    ☹  OOPS!!! The description cannot be empty.");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Date of the task is missing
     */
    public void msgInvalidInputMissingDay() {
        System.out.println("    ☹  OOPS!!! The day cannot be empty.");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Time of the task is missing
     */
    public void msgInvalidInputMissingTime() {
        System.out.println("    ☹  OOPS!!! The time cannot be empty.");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * The task number is not found
     */
    public void msgInvalidTaskNumber() {
        System.out.println("    ☹  OOPS!!! The task number is invalid.");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * The priority is invalid
     */
    public void msgInvalidPriority() {
        System.out.println("    ☹  OOPS!!! The priority is invalid. Choose 1, 2 or 3");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Format of the date is unaccepted
     */
    public void msgInvalidInputWrongDateTimeFormat() {
        System.out.println("    ☹  OOPS!!! Please follow this format:");
        System.out.println("    /by yyyy-mm-dd hh:mm");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Format of the date and time is unaccepted
     */
    public void msgInvalidInputWrongDateTimeStartEndFormat() {
        System.out.println("    ☹  OOPS!!! Please follow this format:");
        System.out.println("    /at yyyy-mm-dd hh:mm hh:mm:");
        System.out.println("_________________________________");
    }

    /**
     * Display message that remind user about invalid input
     * Start time should be before End time
     */
    public void msgInvalidInputTimeStartLaterThanTimeEnd() {
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
    private void msgBuddhaProtectMe() throws IOException {
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
            // FileReader fr = new FileReader("\\My Files\\School
            // Documents\\Repository\\Duke\\src\\main\\resources\\buddha.txt");
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
