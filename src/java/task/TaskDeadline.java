package src.java.task;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class TaskDeadline extends Task {
    private LocalDate taskDate;
    private LocalTime taskTime;

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

    public String getLocalTime_toString(){
        return String.valueOf(taskTime);
    }
}