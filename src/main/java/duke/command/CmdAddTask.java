package duke.command;

import duke.action.ParseDateTime;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.ui.Message;

import java.time.LocalDate;
import java.time.LocalTime;

public class CmdAddTask {

    public static void addTaskToDo(TaskList myList, String userInput) {

        if (userInput.length() <= 5) {
            Message.msgInvalidInputMissingDescription();
            return;
        }

        myList.addItemToDos(userInput.substring(5));

        int lastTaskIndex = myList.getNumOfItem() - 1;
        String taskTypeInString = getTaskType_InString(myList, lastTaskIndex);
        String isDoneInString = getIsDone_InString(myList, lastTaskIndex);
        String taskDetail = getTaskDetail(myList, lastTaskIndex);
        int numOfItem = lastTaskIndex + 1;

        Message.msgAssignTaskToDo(taskTypeInString, isDoneInString, taskDetail, numOfItem);
    }

    public static void addTaskEvent(TaskList myList, String userInput) {
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

    public static void addTaskDeadline(TaskList myList, String userInput) {
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

        int lastTaskIndex = myList.getNumOfItem() - 1;
        String taskTypeInString = getTaskType_InString(myList, lastTaskIndex);
        String isDoneInString = getIsDone_InString(myList, lastTaskIndex);
        String dateInString = getTaskEvent_Date_InString(myList, lastTaskIndex);
        int numOfItem = lastTaskIndex + 1;

        Message.msgAssignTaskEventTaskDate(taskTypeInString, isDoneInString,
                taskDetail, dateInString, numOfItem);
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

        int lastTaskIndex = myList.getNumOfItem() - 1;
        String taskTypeInString = getTaskType_InString(myList, lastTaskIndex);
        String isDoneInString = getIsDone_InString(myList, lastTaskIndex);
        String dateInString = getTaskEvent_Date_InString(myList, lastTaskIndex);
        int numOfItem = lastTaskIndex + 1;
        String getTimeStartInString = getTaskEvent_TimeStart_InString(myList, lastTaskIndex);

        Message.msgAssignTaskEventTaskDateTaskTimeStart(taskTypeInString, isDoneInString,
                taskDetail, dateInString, getTimeStartInString, numOfItem);
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

        int lastTaskIndex = myList.getNumOfItem() - 1;
        String taskTypeInString = getTaskType_InString(myList, lastTaskIndex);
        String isDoneInString = getIsDone_InString(myList, lastTaskIndex);
        String dateInString = getTaskEvent_Date_InString(myList, lastTaskIndex);
        String getTimeStartInString = getTaskEvent_TimeStart_InString(myList, lastTaskIndex);
        String getTimeEndInString = getTaskEvent_TimeEnd_InString(myList, lastTaskIndex);
        int numOfItem = lastTaskIndex + 1;

        Message.msgAssignTaskEventTaskDateTaskTimeStartTaskTimeEnd(taskTypeInString, isDoneInString,
                taskDetail, dateInString, getTimeStartInString, getTimeEndInString, numOfItem);
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

        int lastTaskIndex = myList.getNumOfItem() - 1;
        String taskTypeInString = getTaskType_InString(myList, lastTaskIndex);
        String isDoneInString = getIsDone_InString(myList, lastTaskIndex);
        String dateInString = getTaskDeadline_Date_InString(myList, lastTaskIndex);
        int numOfItem = lastTaskIndex + 1;

        Message.msgAssignTaskDeadlineTaskDate(taskTypeInString, isDoneInString, taskDetail, dateInString, numOfItem);
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

        int lastTaskIndex = myList.getNumOfItem() - 1;
        String taskTypeInString = getTaskType_InString(myList, lastTaskIndex);
        String isDoneInString = getIsDone_InString(myList, lastTaskIndex);
        String dateInString = getTaskDeadline_Date_InString(myList, lastTaskIndex);
        String timeInString = getTaskDeadline_Time_InString(myList, lastTaskIndex);
        int numOfItem = lastTaskIndex + 1;

        Message.msgAssignTaskDeadlineTaskDateTaskTime(taskTypeInString, isDoneInString, taskDetail, dateInString, timeInString, numOfItem);
    }

    private static String getTaskType_InString(TaskList myList, int taskIndex) {
        TaskType taskType = myList.getTaskType(taskIndex);
        return TaskType.taskTypeToString(taskType);
    }

    private static String getIsDone_InString(TaskList myList, int taskIndex) {
        boolean isDone = myList.getTaskDoneStatus(taskIndex);
        return (isDone ? "X" : " ");
    }

    private static String getTaskDetail(TaskList myList, int taskIndex) {
        return myList.getTaskDetail(taskIndex);
    }

    private static String getTaskEvent_Date_InString(TaskList myList, int taskIndex) {
        return myList.getTaskEventTaskDateInString(taskIndex);
    }

    private static String getTaskEvent_TimeStart_InString(TaskList myList, int taskIndex){
        return myList.getTaskEventTaskTimeStartInString(taskIndex);
    }

    private static String getTaskEvent_TimeEnd_InString (TaskList myList, int taskIndex){
        return myList.getTaskEventTaskTimeEndInString(taskIndex);
    }

    private static String getTaskDeadline_Date_InString(TaskList myList, int taskIndex){
        return myList.getTaskDeadLineTaskDateInString(taskIndex);
    }

    private static String getTaskDeadline_Time_InString(TaskList myList, int taskIndex){
        return myList.getTaskDeadLineTaskTimeInString(taskIndex);
    }
}
