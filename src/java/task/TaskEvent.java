package src.java.task;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class TaskEvent extends Task {

    private LocalDate taskDate;
    private LocalTime taskTimeStart;
    private LocalTime taskTimeEnd;

    public TaskEvent(String taskDetail, LocalDate taskDate) {
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.EVENT;
        this.taskDate = taskDate;
    }

    public TaskEvent(String taskDetail, LocalDate taskDate, LocalTime taskTimeStart) {
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.EVENT;
        this.taskDate = taskDate;
        this.taskTimeStart = taskTimeStart;
    }

    public TaskEvent(String taskDetail, LocalDate taskDate, LocalTime taskTimeStart, LocalTime taskTimeEnd) {
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.EVENT;
        this.taskDate = taskDate;
        this.taskTimeStart = taskTimeStart;
        this.taskTimeEnd = taskTimeEnd;
    }

    public LocalDate getLocalDate() {
        return taskDate;
    }

    public String getLocalDate_toString() {
        return String.valueOf(taskDate);
    }

    public String getLocalDate_Day_toString() {
        return String.valueOf(taskDate.getDayOfMonth());
    }

    public String getLocalDate_Month_toString() {
        return String.valueOf(taskDate.getMonth());
    }

    public String getLocalDate_Year_toString() {
        return String.valueOf(taskDate.getYear());
    }

    public LocalTime getLocalTimeStart() {
        return taskTimeStart;
    }

    public LocalTime getLocalTimeEnd() {
        return taskTimeEnd;
    }

    public String getLocalTimeStart_toString() {
        return String.valueOf(taskTimeStart);
    }

    public String getLocalTimeEnd_toString() {
        return String.valueOf(taskTimeEnd);
    }
}
