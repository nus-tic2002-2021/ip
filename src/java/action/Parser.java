package src.java.action;

import src.java.ui.Message;
import src.java.storage.FileAccess;
import src.java.task.TaskList;
import src.java.task.TaskType;
import src.java.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

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
    private Ui ui;

    public Parser(FileAccess fileAccess) {
        this.fileAccess = fileAccess;
        ui = new Message();
    }

    // Start Duke <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Show the opening Greet Message in System.out.println
     */
    public void ShowGreetMessage() {
        ui.msgGreet();
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
    public void OnCreateDuke() {
        boolean isDukeRunning = true;
        String line;
        Scanner in = new Scanner(System.in);
        TaskList myList = new TaskList();
        while (isDukeRunning) {
            line = in.nextLine();
            isDukeRunning = ReadUserCommand(myList, line);
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
    public boolean ReadUserCommand(TaskList myList, String line) {
        try {
            // Guard Condition
            if (line.equals("bye")) {
                return false;
            }

            // for testing new function
            if (line.equals("tf")) {
                System.out.println("XXXXX Test Function XXXXX");
                fileAccess.DeleteProgressFile();
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXX");
            }

            if (line.equals("list")) {
                ShowFullList(myList);
            } else if (line.substring(0, 4).equals("done")) {
                MarkTaskDone(myList, line);
            } else if (line.substring(0, 4).equals("todo")) {
                AddTaskToDo(myList, line);
            } else if (line.substring(0, 4).equals("save")) {
                SaveTask(myList);
            } else if (line.substring(0, 4).equals("load")) {
                // loadTask(myList);
            } else if (line.substring(0, 5).equals("event")) {
                AddTaskEvent(myList, line);
            } else if (line.substring(0, 6).equals("delete")) {
                DeleteTask(myList, line);
            } else if (line.substring(0, 8).equals("deadline")) {
                AddTaskDeadline(myList, line);
            } else {
                ui.msgInvalidInput();
            }
            return true;
        } catch (Exception e) {
            ui.msgInvalidInput();
            ui.msgError(e);
            return true;
        }
    }

    // End Duke <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Show the Bye Message
     * <p>
     * called when Duke program ends
     */
    public void ShowByeMessage() {
        try {
            ui.msgBye();
        } catch (IOException error) {
            ui.msgError(error);
        }
    }

    // ReadUserCommand Support Method <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Show full list of tasks
     *
     * @param myList TaskList that contains the list of task
     */
    private void ShowFullList(TaskList myList) {
        ui.msgList(myList);
    }

    /**
     * Mark a task as done
     *
     * @param myList TaskList that contains the list of task
     * @param line   String that the user type
     */
    private void MarkTaskDone(TaskList myList, String line) {
        int taskNumber = Integer.parseInt(line.substring(5));
        myList.setTaskDone(taskNumber - 1);
        ui.msgMarkDone(myList, taskNumber - 1);
    }

    /**
     * Add a task of ToDo type into the list of task
     *
     * @param myList TaskList that contains the list of task
     * @param line   String that the user type
     */
    private void AddTaskToDo(TaskList myList, String line) {
        if (line.length() <= 5) {
            ui.msgInvalidInputMissingDescription();
        } else {
            myList.addItemToDos(line.substring(5));
            ui.msgAssignTask(myList, myList.getNumOfItem() - 1);
        }
    }

    /**
     * Save the current task list and output a text file
     *
     * @param myList TaskList that contains the list of task
     */
    private void SaveTask(TaskList myList) {
        String listOfTaskString = ReadTaskConvertToString(myList);
        fileAccess.SaveProgressIntoFile(myList, listOfTaskString);
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
    private void AddTaskEvent(TaskList myList, String line) {

        if (line.length() <= 6) {
            ui.msgInvalidInputMissingDescription();
            return;
        }

        if (!line.contains("/at")) {
            ui.msgInvalidInputMissingDay();
            return;
        }

        try {
            String taskDetail = line.substring(6, line.indexOf("/"));
            String dateAndTime = line.substring(line.indexOf("/") + 4);
            String date;
            String timeStart;
            String timeEnd;

            String[] split = ParseDateTime.splitDateAndTime(dateAndTime);
            date = ParseDateTime.ExtractDateFromSplitDateAndTime(split);

            if (ParseDateTime.isDateAndTime(dateAndTime) == 1) {
                AddTaskEvent_LocalDate(myList, taskDetail, date);
            } else if (ParseDateTime.isDateAndTime(dateAndTime) == 2) {
                timeStart = ParseDateTime.ExtracTimeStartFromSplitDateAndTime(split);
                AddTaskEvent_LocalDate_LocalTime(myList, taskDetail, date, timeStart);
            } else {
                timeStart = ParseDateTime.ExtracTimeStartFromSplitDateAndTime(split);
                timeEnd = ParseDateTime.ExtracTimeEndFromSplitDateAndTime(split);
                AddTaskEvent_LocalDate_LocalTime(myList, taskDetail, date, timeStart, timeEnd);
            }

        } catch (Exception e) {
            ui.msgInvalidInputWrongDateTimeStartEndFormat();
        }

    }

    /**
     * Add a task of Event type into TaskList
     *
     * @param myList     TaskList that contains the list of task
     * @param taskDetail String that represents the task detail
     * @param date       String that represents the date of the event
     */
    private void AddTaskEvent_LocalDate(TaskList myList, String taskDetail, String date) {
        LocalDate taskDate = ParseDateTime.toDate(date);

        if (taskDate == null) {
            ui.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        myList.addItemEvent(taskDetail, taskDate);
        ui.msgAssignTaskEvent_TaskDate(myList, myList.getNumOfItem() - 1);
    }

    /**
     * Add a task of Event type into TaskList
     *
     * @param myList     TaskList that contains the list of task
     * @param taskDetail String that represents the task detail
     * @param date       String that represents the date of the event
     * @param timeStart  String that represents the start time of the event
     */
    private void AddTaskEvent_LocalDate_LocalTime(TaskList myList, String taskDetail,
                                                  String date, String timeStart) {

        LocalDate taskDate = ParseDateTime.toDate(date);
        LocalTime taskTimeStart = ParseDateTime.toTime(timeStart);

        if (taskDate == null) {
            ui.msgInvalidInputMissingDay();
            return;
        }

        if (taskTimeStart == null) {
            ui.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        myList.addItemEvent(taskDetail, taskDate, taskTimeStart);
        ui.msgAssignTaskEvent_TaskDate_TaskTimeStart(myList, myList.getNumOfItem() - 1);

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
    private void AddTaskEvent_LocalDate_LocalTime(TaskList myList, String taskDetail,
                                                  String date, String timeStart, String timeEnd) {

        LocalDate taskDate = ParseDateTime.toDate(date);
        LocalTime taskTimeStart = ParseDateTime.toTime(timeStart);
        LocalTime taskTimeEnd = ParseDateTime.toTime(timeEnd);

        if (taskDate == null) {
            ui.msgInvalidInputMissingDay();
            return;
        }

        if (taskTimeStart == null || taskTimeEnd == null) {
            ui.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        if (taskTimeStart.isAfter(taskTimeEnd)) {
            ui.msgInvalidInputTimeStartLaterThanTimeEnd();
            return;
        }

        myList.addItemEvent(taskDetail, taskDate, taskTimeStart, taskTimeEnd);
        ui.msgAssignTaskEvent_TaskDate_TaskTimeStart_TaskTimeEnd(myList, myList.getNumOfItem() - 1);
    }

    /**
     * Delete a task from the user task
     *
     * @param myList TaskList that contains the list of task
     * @param line   String that represents the user input
     */
    private void DeleteTask(TaskList myList, String line) {
        try {
            int taskNumber = Integer.parseInt(line.substring(7));
            ui.msgRemoveItem(myList, taskNumber - 1);
            myList.removeItem(taskNumber - 1);
        } catch (Exception e) {
            ui.msgWrongTaskNumber();
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
    private void AddTaskDeadline(TaskList myList, String line) {

        if (line.length() <= 9) {
            ui.msgInvalidInputMissingDescription();
            return;
        }

        if (!line.contains("/by")) {
            ui.msgInvalidInputMissingDay();
            return;
        }

        try {
            String taskDetail = line.substring(9, line.indexOf("/"));
            String dateAndTime = line.substring(line.indexOf("/") + 4);
            String date;
            String time;

            String[] split = ParseDateTime.splitDateAndTime(dateAndTime);
            date = ParseDateTime.ExtractDateFromSplitDateAndTime(split);

            if (ParseDateTime.isDateAndTime(dateAndTime) == 1) {
                AddTaskDeadline_LocalDate(myList, taskDetail, date);
            } else if (ParseDateTime.isDateAndTime(dateAndTime) == 2) {
                time = ParseDateTime.ExtracTimeFromSplitDateAndTime(split);
                AddTaskDeadline_LocalDate_LocalTime(myList, taskDetail, date, time);
            } else {
                ui.msgInvalidInputWrongDateTimeFormat();
            }

        } catch (Exception e) {
            ui.msgInvalidInputWrongDateTimeFormat();
        }
    }

    /**
     * Add a task of Deadline type into TaskList
     *
     * @param myList     TaskList that contains the list of task
     * @param taskDetail String that represents the task detail
     * @param date       String that represents the date of the deadline
     */
    private void AddTaskDeadline_LocalDate(TaskList myList, String taskDetail, String date) {
        LocalDate taskDate = ParseDateTime.toDate(date);

        if (taskDate == null) {
            ui.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        myList.addItemDeadline(taskDetail, taskDate);
        ui.msgAssignTaskDeadline_TaskDate(myList, myList.getNumOfItem() - 1);
    }

    /**
     * Add a task of Event type into TaskList
     *
     * @param myList     TaskList that contains the list of task
     * @param taskDetail String that represents the task detail
     * @param date       String that represents the date of the deadline
     * @param time       String that represents the time of the deadline
     */
    private void AddTaskDeadline_LocalDate_LocalTime(TaskList myList, String taskDetail,
                                                     String date, String time) {

        LocalDate taskDate = ParseDateTime.toDate(date);
        LocalTime taskTime = ParseDateTime.toTime(time);

        if (taskDate == null) {
            ui.msgInvalidInputMissingDay();
            return;
        }

        if (taskTime == null) {
            ui.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        myList.addItemDeadline(taskDetail, taskDate, taskTime);
        ui.msgAssignTaskDeadline_TaskDate_TaskTime(myList, myList.getNumOfItem() - 1);
    }


    // Other Support Method <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Read a list of task and convert it into string
     *
     * @param myList TaskList that contains the list of task
     */
    private String ReadTaskConvertToString(TaskList myList) {
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


}



