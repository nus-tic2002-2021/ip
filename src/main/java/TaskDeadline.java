import java.sql.Time;
import java.util.Date;

public class TaskDeadline extends Task {
    private Date date;
    private Time time;
    private String dateString; // temporary attribute for Duke Level 4

    public TaskDeadline(String taskDetail) {
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.DEADLINE;
    }

    public TaskDeadline(String taskDetail, Date date, Time time) {
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.DEADLINE;
        this.date = date;
        this.time = time;
    }

    public TaskDeadline(String taskDetail, String dateString) { // temporary constructor for Duke Level 4
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.DEADLINE;
        this.dateString = dateString;
    }

    public String getDateString() { // temporary constructor for Duke Level 4
        return dateString;
    }
}