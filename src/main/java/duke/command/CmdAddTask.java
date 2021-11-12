package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.action.ParseDateTime;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.ui.Message;

/**
 * Command class that handle adding task
 * <p>
 * add 3 types of class: TODO, DEADLINE and EVENT.
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class CmdAddTask {

    /**
     * Add a task of todo type into TaskList
     *
     * @param myList    TaskList that contains the list of task
     * @param userInput String
     */
    public static void addTaskToDo(TaskList myList, String userInput) {

        assert myList != null : "mylist should not be empty";
        assert userInput != null : "userInput should not be empty";

        if (userInput.length() <= 5) {
            Message.msgInvalidInputMissingDescription();
            return;
        }

        myList.addItemToDos(userInput.substring(5));

        int lastTaskIndex = myList.getNumOfItem() - 1;
        String taskTypeInString = getTaskTypeInString(myList, lastTaskIndex);
        String isDoneInString = getIsDoneInString(myList, lastTaskIndex);
        String taskDetail = getTaskDetail(myList, lastTaskIndex);
        int numOfItem = lastTaskIndex + 1;

        Message.msgAssignTaskToDo(taskTypeInString, isDoneInString, taskDetail, numOfItem);
    }

    /**
     * Add a task of event type into TaskList
     *
     * @param myList    TaskList that contains the list of task
     * @param userInput String
     */
    public static void addTaskEvent(TaskList myList, String userInput) {

        assert myList != null : "mylist should not be empty";
        assert userInput != null : "userInput should not be empty";

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

    /**
     * Add a task of deadline type into TaskList
     *
     * @param myList    TaskList that contains the list of task
     * @param userInput String
     */
    public static void addTaskDeadline(TaskList myList, String userInput) {

        assert myList != null : "mylist should not be empty";
        assert userInput != null : "userInput should not be empty";

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

        assert myList != null : "mylist should not be empty";
        assert taskDetail != null : "taskDetail should not be empty";
        assert date != null : "date should not be empty";

        LocalDate taskDate = ParseDateTime.toDate(date);

        if (taskDate == null) {
            Message.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        myList.addItemEvent(taskDetail, taskDate);

        int lastTaskIndex = myList.getNumOfItem() - 1;
        String taskTypeInString = getTaskTypeInString(myList, lastTaskIndex);
        String isDoneInString = getIsDoneInString(myList, lastTaskIndex);
        String dateInString = getTaskEventDateInString(myList, lastTaskIndex);
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

        assert myList != null : "mylist should not be empty";
        assert taskDetail != null : "taskDetail should not be empty";
        assert date != null : "date should not be empty";
        assert timeStart != null : "timeStart should not be empty";

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
        String taskTypeInString = getTaskTypeInString(myList, lastTaskIndex);
        String isDoneInString = getIsDoneInString(myList, lastTaskIndex);
        String dateInString = getTaskEventDateInString(myList, lastTaskIndex);
        int numOfItem = lastTaskIndex + 1;
        String getTimeStartInString = getTaskEventTimeStartInString(myList, lastTaskIndex);

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

        assert myList != null : "mylist should not be empty";
        assert taskDetail != null : "taskDetail should not be empty";
        assert date != null : "date should not be empty";
        assert timeStart != null : "timeStart should not be empty";
        assert timeEnd != null : "timeEnd should not be empty";

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
        String taskTypeInString = getTaskTypeInString(myList, lastTaskIndex);
        String isDoneInString = getIsDoneInString(myList, lastTaskIndex);
        String dateInString = getTaskEventDateInString(myList, lastTaskIndex);
        String getTimeStartInString = getTaskEventTimeStartInString(myList, lastTaskIndex);
        String getTimeEndInString = getTaskEventTimeEndInString(myList, lastTaskIndex);
        int numOfItem = lastTaskIndex + 1;

        Message.msgAssignEventTaskDateTimeStartEnd(taskTypeInString, isDoneInString,
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

        assert myList != null : "mylist should not be empty";
        assert taskDetail != null : "taskDetail should not be empty";
        assert date != null : "date should not be empty";

        LocalDate taskDate = ParseDateTime.toDate(date);

        if (taskDate == null) {
            Message.msgInvalidInputWrongDateTimeFormat();
            return;
        }

        myList.addItemDeadline(taskDetail, taskDate);

        int lastTaskIndex = myList.getNumOfItem() - 1;
        String taskTypeInString = getTaskTypeInString(myList, lastTaskIndex);
        String isDoneInString = getIsDoneInString(myList, lastTaskIndex);
        String dateInString = getTaskDeadlineDateInString(myList, lastTaskIndex);
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

        assert myList != null : "mylist should not be empty";
        assert taskDetail != null : "taskDetail should not be empty";
        assert date != null : "date should not be empty";
        assert time != null : "time should not be empty";

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
        String taskTypeInString = getTaskTypeInString(myList, lastTaskIndex);
        String isDoneInString = getIsDoneInString(myList, lastTaskIndex);
        String dateInString = getTaskDeadlineDateInString(myList, lastTaskIndex);
        String timeInString = getTaskDeadlineTimeInString(myList, lastTaskIndex);
        int numOfItem = lastTaskIndex + 1;

        Message.msgAssignTaskDeadlineTaskDateTaskTime(taskTypeInString,
                isDoneInString, taskDetail, dateInString, timeInString, numOfItem);
    }

    /**
     * Get TaskType of a task using myList and its task index.
     *
     * @param myList    TaskList that contains the list of task
     * @param taskIndex Int that represents the index of the task
     * @return String representation of TaskType of a task in tasklist
     */
    private static String getTaskTypeInString(TaskList myList, int taskIndex) {
        TaskType taskType = myList.getTaskType(taskIndex);
        return TaskType.taskTypeToString(taskType);
    }

    /**
     * Get isDone status of a task in String
     *
     * @param myList    TaskList that contains the list of task
     * @param taskIndex Int that represents the index of the task
     * @return String representation of isDone of a task in tasklist
     */
    private static String getIsDoneInString(TaskList myList, int taskIndex) {
        boolean isDone = myList.getTaskDoneStatus(taskIndex);
        return (isDone ? "X" : " ");
    }

    /**
     * Get taskDetail of a task
     *
     * @param myList    TaskList that contains the list of task
     * @param taskIndex Int that represents the index of the task
     * @return String representation of taskDetail of a task in tasklist
     */
    private static String getTaskDetail(TaskList myList, int taskIndex) {
        return myList.getTaskDetail(taskIndex);
    }

    /**
     * Get date of an EVENT task in String
     *
     * @param myList    TaskList that contains the list of task
     * @param taskIndex Int that represents the index of the task
     * @return String representation of date of a task in tasklist
     */
    private static String getTaskEventDateInString(TaskList myList, int taskIndex) {
        return myList.getTaskEventTaskDateInString(taskIndex);
    }

    /**
     * Get start time of an EVENT task in String
     *
     * @param myList    TaskList that contains the list of task
     * @param taskIndex Int that represents the index of the task
     * @return String representation of start time of a task in tasklist
     */
    private static String getTaskEventTimeStartInString(TaskList myList, int taskIndex) {
        return myList.getTaskEventTaskTimeStartInString(taskIndex);
    }

    /**
     * Get end time of an EVENT task in String
     *
     * @param myList    TaskList that contains the list of task
     * @param taskIndex Int that represents the index of the task
     * @return String representation of end time of a task in tasklist
     */
    private static String getTaskEventTimeEndInString(TaskList myList, int taskIndex) {
        return myList.getTaskEventTaskTimeEndInString(taskIndex);
    }

    /**
     * Get date of an DEADLINE task in String
     *
     * @param myList    TaskList that contains the list of task
     * @param taskIndex Int that represents the index of the task
     * @return String representation of date of a task in tasklist
     */
    private static String getTaskDeadlineDateInString(TaskList myList, int taskIndex) {
        return myList.getTaskDeadLineTaskDateInString(taskIndex);
    }

    /**
     * Get start time of an DEADLINE task in String
     *
     * @param myList    TaskList that contains the list of task
     * @param taskIndex Int that represents the index of the task
     * @return String representation of start time of a task in tasklist
     */
    private static String getTaskDeadlineTimeInString(TaskList myList, int taskIndex) {
        return myList.getTaskDeadLineTaskTimeInString(taskIndex);
    }
}
