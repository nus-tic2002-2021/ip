package src.java.task;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class TaskDeadline extends Task {
    private LocalDate taskDate;
    private LocalTime taskTime;
    private String dateString; // temporary attribute for Duke Level 4

    public TaskDeadline(String taskDetail) {
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.DEADLINE;
    }

    public TaskDeadline(String taskDetail, String dateString) { // temporary constructor for Duke Level 4
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.DEADLINE;
        this.dateString = dateString;
    }

    public TaskDeadline(String taskDetail, LocalDate taskDate) {
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.DEADLINE;
        this.taskDate = taskDate;
    }

    public TaskDeadline(String taskDetail, LocalDate taskDate, LocalTime localTime) {
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.DEADLINE;
        this.taskDate = taskDate;
        this.taskTime = localTime;
    }

    public String getDateString() { // temporary constructor for Duke Level 4
        return dateString;
    }

    public LocalDate getLocalDate(){
        return taskDate;
    }

    public String getLocalDate_toString(){
        return String.valueOf(taskDate);
    }

    public String getLocalDate_Day_toString(){
        return String.valueOf(taskDate.getDayOfMonth());
    }

    public String getLocalDate_Month_toString(){
        return String.valueOf(taskDate.getMonth());
    }

    public String getLocalDate_Year_toString(){
        return String.valueOf(taskDate.getYear());
    }

    public LocalTime getLocalTime(){
        return  taskTime;
    }
}