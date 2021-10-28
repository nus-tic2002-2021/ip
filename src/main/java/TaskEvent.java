package src.main.java;

import java.sql.Time;
import java.util.Date;

public class TaskEvent extends Task {
    private Date date;
    private Time startTime;
    private Time endTime;
    private String dateTime; // temporary attribute for Duke Level 4

    public TaskEvent(String taskDetail) {
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.EVENT;
    }

    public TaskEvent(String taskDetail, Date date, Time startTime, Time endTime) {
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.EVENT;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TaskEvent(String taskDetail, String dayTime) { // temporary constructor for Duke Level 4
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.EVENT;
        this.dateTime = dayTime;
    }

    public String getDayTime() { // temporary constructor for Duke Level 4
        return dateTime;
    }
}
