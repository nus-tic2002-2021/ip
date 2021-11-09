package duke.command;

import duke.action.ParseDateTime;
import duke.task.TaskList;
import duke.ui.Message;

import java.time.LocalDate;
import java.time.LocalTime;

public class CmdAddTask {

    public static void addTaskToDo(TaskList myList, String userInput){
        if (userInput.length() <= 5) {
            Message.msgInvalidInputMissingDescription();
        } else {
            myList.addItemToDos(userInput.substring(5));
            Message.msgAssignTask(myList, myList.getNumOfItem() - 1);
        }
    }

    public static void addTaskEvent(TaskList myList, String userInput){
        if (userInput.length() <= 6) {
            Message.msgInvalidInputMissingDescription();
            return;
        }

        if (!userInput.contains("/at")) {
            Message.msgInvalidInputMissingDay();
            return;
        }

        try {
            String taskDetail = userInput.substring(6, userInput.indexOf("/"));
            String dateAndTime = userInput.substring(userInput.indexOf("/") + 4);
            String date;
            String timeStart;
            String timeEnd;

            String[] split = ParseDateTime.splitDateAndTime(dateAndTime);
            date = ParseDateTime.toExtractDateFromSplitDateAndTime(split);

            if (ParseDateTime.isDateAndTime(dateAndTime) == 1) {
                toAddTaskEvent_localDate(myList, taskDetail, date);
            } else if (ParseDateTime.isDateAndTime(dateAndTime) == 2) {
                timeStart = ParseDateTime.toExtractTimeStartFromSplitDateAndTime(split);
                toAddTaskEvent_localDate_localTime(myList, taskDetail, date, timeStart);
            } else {
                timeStart = ParseDateTime.toExtractTimeStartFromSplitDateAndTime(split);
                timeEnd = ParseDateTime.toExtractTimeEndFromSplitDateAndTime(split);
                toAddTaskEvent_localDate_localTime(myList, taskDetail, date, timeStart, timeEnd);
            }

        } catch (Exception e) {
            Message.msgInvalidInputWrongDateTimeStartEndFormat();
        }
    }

    public static void addTaskDeadline(TaskList myList, String userInput){
        if (userInput.length() <= 9) {
            Message.msgInvalidInputMissingDescription();
            return;
        }

        if (!userInput.contains("/by")) {
            Message.msgInvalidInputMissingDay();
            return;
        }

        try {
            String taskDetail = userInput.substring(9, userInput.indexOf("/"));
            String dateAndTime = userInput.substring(userInput.indexOf("/") + 4);
            String date;
            String time;

            String[] split = ParseDateTime.splitDateAndTime(dateAndTime);
            date = ParseDateTime.toExtractDateFromSplitDateAndTime(split);

            if (ParseDateTime.isDateAndTime(dateAndTime) == 1) {
                toAddTaskDeadline_localDate(myList, taskDetail, date);
            } else if (ParseDateTime.isDateAndTime(dateAndTime) == 2) {
                time = ParseDateTime.toExtractTimeFromSplitDateAndTime(split);
                toAddTaskDeadline_localDate_localTime(myList, taskDetail, date, time);
            } else {
                Message.msgInvalidInputWrongDateTimeFormat();
            }

        } catch (Exception e) {
            Message.msgInvalidInputWrongDateTimeFormat();
        }
    }

    /**
     * Add a task of Event type into TaskList
     *
     * @param myList     TaskList that contains the list of task
     * @param taskDetail String that represents the task detail
     * @param date       String that represents the date of the event
     */
    private static void toAddTaskEvent_localDate(TaskList myList, String taskDetail, String date) {
        LocalDate taskDate = ParseDateTime.toDate(date);

        if (taskDate == null) {
            Message.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        myList.addItemEvent(taskDetail, taskDate);
        Message.msgAssignTaskEventTaskDate(myList, myList.getNumOfItem() - 1);
    }

    /**
     * Add a task of Event type into TaskList
     *
     * @param myList     TaskList that contains the list of task
     * @param taskDetail String that represents the task detail
     * @param date       String that represents the date of the event
     * @param timeStart  String that represents the start time of the event
     */
    private static void toAddTaskEvent_localDate_localTime(TaskList myList, String taskDetail,
                                                    String date, String timeStart) {

        LocalDate taskDate = ParseDateTime.toDate(date);
        LocalTime taskTimeStart = ParseDateTime.toTime(timeStart);

        if (taskDate == null) {
            Message.msgInvalidInputMissingDay();
            return;
        }

        if (taskTimeStart == null) {
            Message.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        myList.addItemEvent(taskDetail, taskDate, taskTimeStart);
        Message.msgAssignTaskEventTaskDateTaskTimeStart(myList, myList.getNumOfItem() - 1);
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
    private static void toAddTaskEvent_localDate_localTime(TaskList myList, String taskDetail,
                                                    String date, String timeStart, String timeEnd) {

        LocalDate taskDate = ParseDateTime.toDate(date);
        LocalTime taskTimeStart = ParseDateTime.toTime(timeStart);
        LocalTime taskTimeEnd = ParseDateTime.toTime(timeEnd);

        if (taskDate == null) {
            Message.msgInvalidInputMissingDay();
            return;
        }

        if (taskTimeStart == null || taskTimeEnd == null) {
            Message.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        if (taskTimeStart.isAfter(taskTimeEnd)) {
            Message.msgInvalidInputTimeStartLaterThanTimeEnd();
            return;
        }

        myList.addItemEvent(taskDetail, taskDate, taskTimeStart, taskTimeEnd);
        Message.msgAssignTaskEventTaskDateTaskTimeStartTaskTimeEnd(myList, myList.getNumOfItem() - 1);
    }

    /**
     * Add a task of Deadline type into TaskList
     *
     * @param myList     TaskList that contains the list of task
     * @param taskDetail String that represents the task detail
     * @param date       String that represents the date of the deadline
     */
    private static void toAddTaskDeadline_localDate(TaskList myList, String taskDetail, String date) {
        LocalDate taskDate = ParseDateTime.toDate(date);

        if (taskDate == null) {
            Message.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        myList.addItemDeadline(taskDetail, taskDate);
        Message.msgAssignTaskDeadlineTaskDate(myList, myList.getNumOfItem() - 1);
    }

    /**
     * Add a task of Event type into TaskList
     *
     * @param myList     TaskList that contains the list of task
     * @param taskDetail String that represents the task detail
     * @param date       String that represents the date of the deadline
     * @param time       String that represents the time of the deadline
     */
    private static void toAddTaskDeadline_localDate_localTime(TaskList myList, String taskDetail,
                                                       String date, String time) {

        LocalDate taskDate = ParseDateTime.toDate(date);
        LocalTime taskTime = ParseDateTime.toTime(time);

        if (taskDate == null) {
            Message.msgInvalidInputMissingDay();
            return;
        }

        if (taskTime == null) {
            Message.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        myList.addItemDeadline(taskDetail, taskDate, taskTime);
        Message.msgAssignTaskDeadlineTaskDateTaskTime(myList, myList.getNumOfItem() - 1);
    }
}
