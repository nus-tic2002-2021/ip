package duke.action;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.storage.FileAccess;
import duke.task.TaskList;
import duke.task.TaskPriority;
import duke.task.TaskType;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * Main logic of the Duke Program
 * Handles the sequence of program flow
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class Parser {

    private FileAccess fileAccess;
    private Ui uiMessage;

    /**
     * Constructor
     */
    public Parser(FileAccess fileAccess) {
        this.fileAccess = fileAccess;
        uiMessage = new Message();
    }

    // Start Duke <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Show the opening Greet Message in System.out.println
     */
    public void showGreetMessage() {
        uiMessage.msgGreet();
    }

    // Run Duke <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Create main sequence of the Duke program
     * This section iterate until "bye" is called
     * <p>
     * Current Supported Duke Command List:
     * - list
     * - done
     * - todo
     * - save
     * - load
     * - event
     * - delete
     * - deadline
     */
    public void onCreateDuke() {
        boolean isDukeRunning = true;
        String line;
        Scanner in = new Scanner(System.in);
        TaskList myList = new TaskList();
        while (isDukeRunning) {
            line = in.nextLine();
            isDukeRunning = shouldReadUserCommand(myList, line);
        }
        in.close();
    }

    /**
     * Read a single user input and respond accordingly
     *
     * @param myList TaskList that contains the list of task
     * @param line   String that the user type
     * @return boolean Return false if user type Bye, else return true
     */
    public boolean shouldReadUserCommand(TaskList myList, String line) {
        try {
            // Guard Condition
            if (line.equals("bye")) {
                return false;
            }

            // for testing new function
            if (line.equals("tf")) {
                System.out.println("XXXXX Test Function XXXXX");
                fileAccess.deleteProgressFile();
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXX");
            }

            if (line.equals("list")) {
                showFullList(myList);
            } else if (line.startsWith("set")) {
                toSetPriorityTask(myList, line);
            } else if (line.startsWith("done")) {
                toMarkTaskDone(myList, line);
            } else if (line.startsWith("todo")) {
                toAddTaskToDo(myList, line);
            } else if (line.startsWith("save")) {
                toSaveTask(myList);
            } else if (line.startsWith("load")) {
                // loadTask(myList);
            } else if (line.startsWith("find")) {
                toFindTask(myList, line);
            } else if (line.startsWith("event")) {
                toAddTaskEvent(myList, line);
            } else if (line.startsWith("delete")) {
                toDeleteTask(myList, line);
            } else if (line.startsWith("deadline")) {
                toAddTaskDeadline(myList, line);
            } else {
                uiMessage.msgInvalidInput();
            }
            return true;
        } catch (Exception e) {
            uiMessage.msgInvalidInput();
            uiMessage.msgError(e);
            return true;
        }
    }

    // End Duke <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Show the Bye Message
     * <p>
     * called when Duke program ends
     */
    public void showByeMessage() {
        try {
            uiMessage.msgBye();
        } catch (IOException error) {
            uiMessage.msgError(error);
        }
    }

    // ReadUserCommand Support Method <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Show full list of tasks
     *
     * @param myList TaskList that contains the list of task
     */
    private void showFullList(TaskList myList) {
        uiMessage.msgList(myList);
    }

    /**
     * Ask user which task to set priority and change the priority accordingly
     * -- Ask user which task to be changed
     * -- Ask user what is the new priority
     * -- Update the task with new priority
     *
     * @param myList TaskList that contains the list of task
     * @param line   String that the user type
     */
    private void toSetPriorityTask(TaskList myList, String line) {

        boolean isTaskNumberValid = false;
        String userInputTaskNumber = "";

        boolean isPriorityNumberValid = false;
        String userInputNewPriorityNumber = "";

        Scanner in = new Scanner(System.in);

        while (isTaskNumberValid == false) {
            uiMessage.msgAskUserSetTaskPriority();
            userInputTaskNumber = in.nextLine();
            isTaskNumberValid = hasValidTaskNumber(myList, userInputTaskNumber);
        }

        int taskNumber = Integer.parseInt(userInputTaskNumber);
        uiMessage.msgDashLines();

        while (isPriorityNumberValid == false) {
            uiMessage.msgAskUserWhatPriority();
            userInputNewPriorityNumber = in.nextLine();
            isPriorityNumberValid = hasValidPriorityNumber(userInputNewPriorityNumber);
        }

        int newPriorityInteger = Integer.parseInt(userInputNewPriorityNumber);

        TaskPriority taskPriority = TaskPriority.convertIntToPriority(newPriorityInteger);
        toUpdatePriority(myList, taskNumber - 1, taskPriority);
    }

    /**
     * Ask user which task to set priority and change the priority accordingly
     * -- Ask user which task to be changed
     * -- Ask user what is the new priority
     * -- Update the task with new priority
     *
     * @param myList       TaskList that contains the list of task
     * @param taskNumber   int that indicates the task number
     * @param taskPriority TaskPriority(new one) that want to be set
     */
    private void toUpdatePriority(TaskList myList, int taskNumber, TaskPriority taskPriority) {
        myList.setTaskPriority(taskNumber, taskPriority);
        uiMessage.msgSetPriority(myList, taskNumber); //
    }

    /**
     * Mark a task as done
     *
     * @param myList TaskList that contains the list of task
     * @param line   String that the user type
     */
    private void toMarkTaskDone(TaskList myList, String line) {
        int taskNumber = Integer.parseInt(line.substring(5));
        myList.setTaskDone(taskNumber - 1);
        uiMessage.msgMarkDone(myList, taskNumber - 1);
    }

    /**
     * Add a task of ToDo type into the list of task
     *
     * @param myList TaskList that contains the list of task
     * @param line   String that the user type
     */
    private void toAddTaskToDo(TaskList myList, String line) {
        if (line.length() <= 5) {
            uiMessage.msgInvalidInputMissingDescription();
        } else {
            myList.addItemToDos(line.substring(5));
            uiMessage.msgAssignTask(myList, myList.getNumOfItem() - 1);
        }
    }

    /**
     * Save the current task list and output a text file
     *
     * @param myList TaskList that contains the list of task
     */
    private void toSaveTask(TaskList myList) {
        String listOfTaskString = toReadTaskConvertToString(myList);
        fileAccess.saveProgressIntoFile(myList, listOfTaskString);
    }

    /**
     * Find the Task requested
     *
     * @param myList TaskList that contains the list of task
     */
    private void toFindTask(TaskList myList, String line) {
        if (line.length() <= 5) {
            uiMessage.msgInvalidFindTerm();
            return;
        }

        String searchTerm = line.substring(5);
        ArrayList<Integer> taskNumberContainingSearchTerm = new ArrayList<>();
        for (int i = 0; i < myList.getNumOfItem(); i++) {
            String taskDetail = myList.getTaskDetail(i);
            if (hasStringContain(taskDetail, searchTerm)) {
                taskNumberContainingSearchTerm.add(i);
            }
        }

        if (taskNumberContainingSearchTerm.isEmpty()) {
            uiMessage.msgInvalidFindTerm();
            return;
        }

        uiMessage.msgTaskFound(myList, taskNumberContainingSearchTerm);
    }

    /**
     * Add a task of Event type into TaskList
     * <p>
     * Identify the task date and time that the user input
     * Calls for respective AddTaskEvent_XXX to create a task object in TaskList afterwards
     *
     * @param myList TaskList that contains the list of task
     * @param line   String that the user type
     */
    private void toAddTaskEvent(TaskList myList, String line) {

        if (line.length() <= 6) {
            uiMessage.msgInvalidInputMissingDescription();
            return;
        }

        if (!line.contains("/at")) {
            uiMessage.msgInvalidInputMissingDay();
            return;
        }

        try {
            String taskDetail = line.substring(6, line.indexOf("/"));
            String dateAndTime = line.substring(line.indexOf("/") + 4);
            String date;
            String timeStart;
            String timeEnd;

            String[] split = ParseDateTime.splitDateAndTime(dateAndTime);
            date = ParseDateTime.toExtractDateFromSplitDateAndTime(split);

            if (ParseDateTime.isDateAndTime(dateAndTime) == 1) {
                toAddTaskEvent_localDate(myList, taskDetail, date);
            } else if (ParseDateTime.isDateAndTime(dateAndTime) == 2) {
                timeStart = ParseDateTime.toExtracTimeStartFromSplitDateAndTime(split);
                toAddTaskEvent_localDate_localTime(myList, taskDetail, date, timeStart);
            } else {
                timeStart = ParseDateTime.toExtracTimeStartFromSplitDateAndTime(split);
                timeEnd = ParseDateTime.toExtracTimeEndFromSplitDateAndTime(split);
                toAddTaskEvent_localDate_localTime(myList, taskDetail, date, timeStart, timeEnd);
            }

        } catch (Exception e) {
            uiMessage.msgInvalidInputWrongDateTimeStartEndFormat();
        }

    }

    /**
     * Add a task of Event type into TaskList
     *
     * @param myList     TaskList that contains the list of task
     * @param taskDetail String that represents the task detail
     * @param date       String that represents the date of the event
     */
    private void toAddTaskEvent_localDate(TaskList myList, String taskDetail, String date) {
        LocalDate taskDate = ParseDateTime.toDate(date);

        if (taskDate == null) {
            uiMessage.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        myList.addItemEvent(taskDetail, taskDate);
        uiMessage.msgAssignTaskEventTaskDate(myList, myList.getNumOfItem() - 1);
    }

    /**
     * Add a task of Event type into TaskList
     *
     * @param myList     TaskList that contains the list of task
     * @param taskDetail String that represents the task detail
     * @param date       String that represents the date of the event
     * @param timeStart  String that represents the start time of the event
     */
    private void toAddTaskEvent_localDate_localTime(TaskList myList, String taskDetail,
                                                    String date, String timeStart) {

        LocalDate taskDate = ParseDateTime.toDate(date);
        LocalTime taskTimeStart = ParseDateTime.toTime(timeStart);

        if (taskDate == null) {
            uiMessage.msgInvalidInputMissingDay();
            return;
        }

        if (taskTimeStart == null) {
            uiMessage.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        myList.addItemEvent(taskDetail, taskDate, taskTimeStart);
        uiMessage.msgAssignTaskEventTaskDateTaskTimeStart(myList, myList.getNumOfItem() - 1);

    }

    /**
     * Add a task of Event type into TaskList
     *
     * @param myList     TaskList that contains the list of task
     * @param taskDetail String that represents the task detail
     * @param date       String that represents the date of the event
     * @param timeStart  String that represents the start time of the event
     * @param timeEnd    String that represents the end time of the event
     */
    private void toAddTaskEvent_localDate_localTime(TaskList myList, String taskDetail,
                                                    String date, String timeStart, String timeEnd) {

        LocalDate taskDate = ParseDateTime.toDate(date);
        LocalTime taskTimeStart = ParseDateTime.toTime(timeStart);
        LocalTime taskTimeEnd = ParseDateTime.toTime(timeEnd);

        if (taskDate == null) {
            uiMessage.msgInvalidInputMissingDay();
            return;
        }

        if (taskTimeStart == null || taskTimeEnd == null) {
            uiMessage.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        if (taskTimeStart.isAfter(taskTimeEnd)) {
            uiMessage.msgInvalidInputTimeStartLaterThanTimeEnd();
            return;
        }

        myList.addItemEvent(taskDetail, taskDate, taskTimeStart, taskTimeEnd);
        uiMessage.msgAssignTaskEventTaskDateTaskTimeStartTaskTimeEnd(myList, myList.getNumOfItem() - 1);
    }

    /**
     * Delete a task from the user task
     *
     * @param myList TaskList that contains the list of task
     * @param line   String that represents the user input
     */
    private void toDeleteTask(TaskList myList, String line) {
        try {
            int taskNumber = Integer.parseInt(line.substring(7));
            uiMessage.msgRemoveItem(myList, taskNumber - 1);
            myList.removeItem(taskNumber - 1);
        } catch (Exception e) {
            uiMessage.msgInvalidTaskNumber();
        }
    }

    /**
     * Add a task of Deadline type into TaskList
     * <p>
     * Identify the task date and time that the user input
     * Calls for respective AddTaskEvent_XXX to create a task object in TaskList afterwards
     *
     * @param myList TaskList that contains the list of task
     * @param line   String that represents the user input
     */
    private void toAddTaskDeadline(TaskList myList, String line) {

        if (line.length() <= 9) {
            uiMessage.msgInvalidInputMissingDescription();
            return;
        }

        if (!line.contains("/by")) {
            uiMessage.msgInvalidInputMissingDay();
            return;
        }

        try {
            String taskDetail = line.substring(9, line.indexOf("/"));
            String dateAndTime = line.substring(line.indexOf("/") + 4);
            String date;
            String time;

            String[] split = ParseDateTime.splitDateAndTime(dateAndTime);
            date = ParseDateTime.toExtractDateFromSplitDateAndTime(split);

            if (ParseDateTime.isDateAndTime(dateAndTime) == 1) {
                toAddTaskDeadline_localDate(myList, taskDetail, date);
            } else if (ParseDateTime.isDateAndTime(dateAndTime) == 2) {
                time = ParseDateTime.toExtracTimeFromSplitDateAndTime(split);
                toAddTaskDeadline_localDate_localTime(myList, taskDetail, date, time);
            } else {
                uiMessage.msgInvalidInputWrongDateTimeFormat();
            }

        } catch (Exception e) {
            uiMessage.msgInvalidInputWrongDateTimeFormat();
        }
    }

    /**
     * Add a task of Deadline type into TaskList
     *
     * @param myList     TaskList that contains the list of task
     * @param taskDetail String that represents the task detail
     * @param date       String that represents the date of the deadline
     */
    private void toAddTaskDeadline_localDate(TaskList myList, String taskDetail, String date) {
        LocalDate taskDate = ParseDateTime.toDate(date);

        if (taskDate == null) {
            uiMessage.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        myList.addItemDeadline(taskDetail, taskDate);
        uiMessage.msgAssignTaskDeadlineTaskDate(myList, myList.getNumOfItem() - 1);
    }

    /**
     * Add a task of Event type into TaskList
     *
     * @param myList     TaskList that contains the list of task
     * @param taskDetail String that represents the task detail
     * @param date       String that represents the date of the deadline
     * @param time       String that represents the time of the deadline
     */
    private void toAddTaskDeadline_localDate_localTime(TaskList myList, String taskDetail,
                                                       String date, String time) {

        LocalDate taskDate = ParseDateTime.toDate(date);
        LocalTime taskTime = ParseDateTime.toTime(time);

        if (taskDate == null) {
            uiMessage.msgInvalidInputMissingDay();
            return;
        }

        if (taskTime == null) {
            uiMessage.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        myList.addItemDeadline(taskDetail, taskDate, taskTime);
        uiMessage.msgAssignTaskDeadlineTaskDateTaskTime(myList, myList.getNumOfItem() - 1);
    }


    // Other Support Method <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Read a list of task and convert it into string
     *
     * @param myList TaskList that contains the list of task
     */
    private String toReadTaskConvertToString(TaskList myList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myList.getNumOfItem(); i++) {
            String taskDetail = myList.getTaskDetail(i);
            boolean isDone = myList.getTaskDoneStatus(i);
            String isDoneString = (isDone ? "1" : "0");
            String taskType = TaskType.taskTypeToString(myList.getTaskType(i));

            sb.append(taskType).append("|");
            sb.append(isDoneString).append("|");
            sb.append(taskDetail).append("|\n");
        }
        return sb.toString();
    }

    /**
     * Check the validity of task number in String
     *
     * @param myList          TaskList that contains the list of task
     * @param userInputString String
     * @return boolean True if userInput is a valid task number; False otherwise
     */
    private boolean hasValidTaskNumber(TaskList myList, String userInputString) {

        try {
            int userInputInt = Integer.parseInt(userInputString);
            int numOfItem = myList.getNumOfItem();
            if (userInputInt <= numOfItem) {
                return true;
            }
        } catch (Exception e) {
            uiMessage.msgInvalidTaskNumber();
            return false;
        }
        uiMessage.msgInvalidTaskNumber();
        return false;
    }

    private boolean hasValidPriorityNumber(String userInputPriorityS) {
        try {
            TaskPriority taskPriority = TaskPriority.convertStringToPriority(userInputPriorityS);
            if (taskPriority == TaskPriority.HIGH) {
                return true;
            } else if (taskPriority == TaskPriority.MEDIUM) {
                return true;
            } else if (taskPriority == TaskPriority.LOW) {
                return true;
            }
        } catch (Exception e) {
            uiMessage.msgInvalidPriority();
            return false;
        }
        uiMessage.msgInvalidPriority();
        return false;
    }

    private boolean hasStringContain(String sourceString, String searchString) {
        String pattern = "\\b" + searchString + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(sourceString);
        return m.find();
    }
}



