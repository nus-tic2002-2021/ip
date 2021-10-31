package src.java.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.*;

/**
 * Create an instance of list of task
 * <p>
 * Create task object
 * Manipulate task object creation, modification and deletion
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class TaskList {
    private List<Task> list;

    // Initialize & Add Item & Remove Item <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public TaskList() {
        list = new ArrayList<Task>();
    }

    /**
     * Add a task of type ToDo
     *
     * @param taskDetail String that represents the task detail
     */
    public void addItemToDos(String taskDetail) {
        list.add(new TaskToDos(taskDetail));
    }

    /**
     * Add a task of type Deadline
     *
     * @param taskDetail String that represents the task detail
     * @param taskDate   LocalDate that represents the task date
     */
    public void addItemDeadline(String taskDetail, LocalDate taskDate) {
        list.add(new TaskDeadline(taskDetail, taskDate));
    }

    /**
     * Add a task of type Deadline
     *
     * @param taskDetail String that represents the task detail
     * @param taskDate   LocalDate that represents the task date
     * @param taskTime   LocalTime that represents the task time
     */
    public void addItemDeadline(String taskDetail, LocalDate taskDate, LocalTime taskTime) {
        list.add(new TaskDeadline(taskDetail, taskDate, taskTime));
    }

    /**
     * Add a task of type Event
     *
     * @param taskDetail String that represents the task detail
     * @param taskDate   LocalDate that represents the task date
     */
    public void addItemEvent(String taskDetail, LocalDate taskDate) {
        list.add(new TaskEvent(taskDetail, taskDate));
    }

    /**
     * Add a task of type Event
     *
     * @param taskDetail    String that represents the task detail
     * @param taskDate      LocalDate that represents the task date
     * @param taskTimeStart LocalTime that represents the task start time
     */
    public void addItemEvent(String taskDetail, LocalDate taskDate, LocalTime taskTimeStart) {
        list.add(new TaskEvent(taskDetail, taskDate, taskTimeStart));
    }

    /**
     * Add a task of type Event
     *
     * @param taskDetail    String that represents the task detail
     * @param taskDate      LocalDate that represents the task date
     * @param taskTimeStart LocalTime that represents the task start time
     * @param taskTimeEnd   LocalTime that represents the task end time
     */
    public void addItemEvent(String taskDetail, LocalDate taskDate, LocalTime taskTimeStart, LocalTime taskTimeEnd) {
        list.add(new TaskEvent(taskDetail, taskDate, taskTimeStart, taskTimeEnd));
    }

    /**
     * Delete a task from tasklist
     *
     * @param index Int that represents the index in a task list
     */
    public void removeItem(int index) {
        list.remove(index);
    }

    // Getter <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Return a task from tasklist
     *
     * @param index Int that represents the index in a task list
     * @return Task at the index
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Return a task detail from a task of tasklist
     *
     * @param index Int that represents the index in a task list
     * @return String that represents the task detail
     */
    public String getTaskDetail(int index) {
        Task task = list.get(index);
        return task.getTaskDetail();
    }

    /**
     * Return a boolean done status from a task of tasklist
     *
     * @param index Int that represents the index in a task list
     * @return boolean that represents the task done status
     */
    public boolean getTaskDoneStatus(int index) {
        Task task = list.get(index);
        return task.getDoneStatus();
    }

    /**
     * Return a taskDate in String from a task of tasklist
     *
     * @param index Int that represents the index in a task list
     * @return String that represents the task date
     */
    public String getTaskEvent_TaskDate_InString(int index) {
        TaskEvent task = (TaskEvent) list.get(index);
        return task.getLocalDate_toString();
    }

    /**
     * Return year from a taskDate in String from a task of tasklist
     *
     * @param index Int that represents the index in a task list
     * @return String that represents the year from taskDate
     */
    public String getTaskEvent_TaskDate_Year_InString(int index) {
        TaskEvent task = (TaskEvent) list.get(index);
        return task.getLocalDate_Year_toString();
    }

    /**
     * Return month from a taskDate in String from a task of tasklist
     *
     * @param index Int that represents the index in a task list
     * @return String that represents the month from taskDate
     */
    public String getTaskEvent_TaskDate_Month_InString(int index) {
        TaskEvent task = (TaskEvent) list.get(index);
        return task.getLocalDate_Month_toString();
    }

    /**
     * Return day from a taskDate in String from a task of tasklist
     *
     * @param index Int that represents the index in a task list
     * @return String that represents the day from taskDate
     */
    public String getTaskEvent_TaskDate_Day_InString(int index) {
        TaskEvent task = (TaskEvent) list.get(index);
        return task.getLocalDate_Day_toString();
    }

    /**
     * Return start time in String from a task of tasklist
     *
     * @param index Int that represents the index in a task list
     * @return String that represents the start time from TaskTime
     */
    public String getTaskEvent_TaskTimeStart_InString(int index) {
        TaskEvent task = (TaskEvent) list.get(index);
        return task.getLocalTimeStart_toString();
    }

    /**
     * Return end time in String from a task of tasklist
     *
     * @param index Int that represents the index in a task list
     * @return String that represents the end time from TaskTime
     */
    public String getTaskEvent_TaskTimeEnd_InString(int index) {
        TaskEvent task = (TaskEvent) list.get(index);
        return task.getLocalTimeEnd_toString();
    }

    /**
     * Return a taskDate in String from a task of tasklist
     *
     * @param index Int that represents the index in a task list
     * @return String that represents the task date
     */
    public String getTaskDeadLine_TaskDate_InString(int index) {
        TaskDeadline task = (TaskDeadline) list.get(index);
        return task.getLocalDate_toString();
    }

    /**
     * Return year from a taskDate in String from a task of tasklist
     *
     * @param index Int that represents the index in a task list
     * @return String that represents the year from TaskDate
     */
    public String getTaskDeadLine_TaskDate_Year_InString(int index) {
        TaskDeadline task = (TaskDeadline) list.get(index);
        return task.getLocalDate_Year_toString();
    }

    /**
     * Return month from a taskDate in String from a task of tasklist
     *
     * @param index Int that represents the index in a task list
     * @return String that represents the month from TaskDate
     */
    public String getTaskDeadLine_TaskDate_Month_InString(int index) {
        TaskDeadline task = (TaskDeadline) list.get(index);
        return task.getLocalDate_Month_toString();
    }

    /**
     * Return day from a taskDate in String from a task of tasklist
     *
     * @param index Int that represents the index in a task list
     * @return String that represents the day from TaskDate
     */
    public String getTaskDeadLine_TaskDate_Day_InString(int index) {
        TaskDeadline task = (TaskDeadline) list.get(index);
        return task.getLocalDate_Day_toString();
    }

    /**
     * Return TaskTime in String from a task of tasklist
     *
     * @param index Int that represents the index in a task list
     * @return String that represents the TaskTime
     */
    public String getTaskDeadLine_TaskTime_InString(int index) {
        TaskDeadline task = (TaskDeadline) list.get(index);
        return task.getLocalTime_toString();
    }

    /**
     * Return the number of item in a tasklist
     *
     * @return Int that represents the number of item in a tasklist
     */
    public int getNumOfItem() {
        return list.size();
    }

    /**
     * Return TaskType from a task of tasklist
     *
     * @param index Int that represents the index in a task list
     * @return TaskType that represents the type of task
     */
    public TaskType getTaskType(int index) {
        Task task = list.get(index);
        return task.getTaskType();
    }

    // Setter <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Return TaskType from a task of tasklist
     *
     * @param index Int that represents the index in a task list
     * @return TaskType that represents the type of task
     */
    public void setTaskDone(int index) {
        Task task = list.get(index);
        task.setTaskCompleted();
    }
}
