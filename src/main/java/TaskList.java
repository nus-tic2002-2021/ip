package src.main.java;

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

    public void addItemDeadline(String taskDetail, Date date, Time time) {
        list.add(new TaskDeadline(taskDetail, date, time));
    }

    public void addItemDeadline(String taskDetail, String dateString) { // temporary constructor for Duke Level 4
        list.add(new TaskDeadline(taskDetail, dateString));
    }

    public void addItemEvent(String taskDetail, Date date, Time startTime, Time endTime) {
        list.add(new TaskEvent(taskDetail, date, startTime, endTime));
    }

    public void addItemEvent(String taskDetail, String daytime) { // temporary constructor for Duke Level 4
        list.add(new TaskEvent(taskDetail, daytime));
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

    public String getTaskDeadlineDateString(int index) { // temporary method for Duke Level 4
        TaskDeadline task = (TaskDeadline) list.get(index);
        return task.getDateString();
    }

    public String getTaskEventDateTimeString(int index) { // temporary method for Duke Level 4
        TaskEvent task = (TaskEvent) list.get(index);
        return task.getDayTime();
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
