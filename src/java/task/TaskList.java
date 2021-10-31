package src.java.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.*;
import java.sql.Time;
import java.util.Date;

public class TaskList {
    private List<Task> list;

    // Initialize & Add Item & Remove Item <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public void addItemToDos(String taskDetail) {
        list.add(new TaskToDos(taskDetail));
    }

    public void addItemDeadline(String taskDetail, LocalDate taskDate) {
        list.add(new TaskDeadline(taskDetail, taskDate));
    }

    public void addItemDeadline(String taskDetail, LocalDate taskDate, LocalTime taskTime) {
        list.add(new TaskDeadline(taskDetail, taskDate, taskTime));
    }

    public void addItemEvent(String taskDetail, LocalDate taskDate) {
        list.add(new TaskEvent(taskDetail, taskDate));
    }

    public void addItemEvent(String taskDetail, LocalDate taskDate, LocalTime taskTimeStart) {
        list.add(new TaskEvent(taskDetail, taskDate, taskTimeStart));
    }

    public void addItemEvent(String taskDetail, LocalDate taskDate, LocalTime taskTimeStart, LocalTime taskTimeEnd) {
        list.add(new TaskEvent(taskDetail, taskDate, taskTimeStart, taskTimeEnd));
    }

    public void removeItem(int index) {
        list.remove(index);
    }

    // Getter <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public Task getTask(int index) {
        return list.get(index);
    }

    public String getTaskDetail(int index) {
        Task task = list.get(index);
        return task.getTaskDetail();
    }

    public boolean getTaskDoneStatus(int index) {
        Task task = list.get(index);
        return task.getDoneStatus();
    }

    public String getTaskEvent_TaskDate_InString(int index) {
        TaskEvent task = (TaskEvent) list.get(index);
        return task.getLocalDate_toString();
    }

    public String getTaskEvent_TaskDate_Year_InString(int index) {
        TaskEvent task = (TaskEvent) list.get(index);
        return task.getLocalDate_Year_toString();
    }

    public String getTaskEvent_TaskDate_Month_InString(int index) {
        TaskEvent task = (TaskEvent) list.get(index);
        return task.getLocalDate_Month_toString();
    }

    public String getTaskEvent_TaskDate_Day_InString(int index) {
        TaskEvent task = (TaskEvent) list.get(index);
        return task.getLocalDate_Day_toString();
    }

    public String getTaskEvent_TaskTimeStart_InString(int index) {
        TaskEvent task = (TaskEvent) list.get(index);
        return task.getLocalTimeStart_toString();
    }

    public String getTaskEvent_TaskTimeEnd_InString(int index) {
        TaskEvent task = (TaskEvent) list.get(index);
        return task.getLocalTimeEnd_toString();
    }

    public String getTaskDeadLine_TaskDate_InString(int index) {
        TaskDeadline task = (TaskDeadline) list.get(index);
        return task.getLocalDate_toString();
    }

    public String getTaskDeadLine_TaskDate_Year_InString(int index) {
        TaskDeadline task = (TaskDeadline) list.get(index);
        return task.getLocalDate_Year_toString();
    }

    public String getTaskDeadLine_TaskDate_Month_InString(int index) {
        TaskDeadline task = (TaskDeadline) list.get(index);
        return task.getLocalDate_Month_toString();
    }

    public String getTaskDeadLine_TaskDate_Day_InString(int index) {
        TaskDeadline task = (TaskDeadline) list.get(index);
        return task.getLocalDate_Day_toString();
    }

    public String getTaskDeadLine_TaskTime_InString(int index) {
        TaskDeadline task = (TaskDeadline) list.get(index);
        return task.getLocalTime_toString();
    }

    public int getNumOfItem() {
        return list.size();
    }

    public TaskType getTaskType(int index) {
        Task task = list.get(index);
        return task.getTaskType();
    }

    // Setter <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void setTaskDone(int index) {
        Task task = list.get(index);
        task.setTaskCompleted();
    }
}
