package src.java.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Create an instance of event class
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

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

    /**
     * Return LocalDate of a task
     *
     * @return LocalDate that represents the taskDate
     */
    public LocalDate getLocalDate() {
        return taskDate;
    }

    /**
     * Return LocalDate of a task in String
     *
     * @return String that represents the taskDate
     */
    public String getLocalDate_toString() {
        return String.valueOf(taskDate);
    }

    /**
     * Return day from LocalDate of a task in String
     *
     * @return String that represents the day of taskDate
     */
    public String getLocalDate_Day_toString() {
        return String.valueOf(taskDate.getDayOfMonth());
    }

    /**
     * Return month from LocalDate of a task in String
     *
     * @return String that represents the month of taskDate
     */
    public String getLocalDate_Month_toString() {
        return String.valueOf(taskDate.getMonth());
    }

    /**
     * Return year from LocalDate of a task in String
     *
     * @return String that represents the year of taskDate
     */
    public String getLocalDate_Year_toString() {
        return String.valueOf(taskDate.getYear());
    }

    /**
     * Return start time of LocalTime of a task
     *
     * @return LocalTime that represents the start time of taskTime
     */
    public LocalTime getLocalTimeStart() {
        return taskTimeStart;
    }

    /**
     * Return end time of LocalTime of a task
     *
     * @return LocalTime that represents the end time of taskTime
     */
    public LocalTime getLocalTimeEnd() {
        return taskTimeEnd;
    }

    /**
     * Return start time of LocalTime of a task in String
     *
     * @return String that represents the start time of taskTime
     */
    public String getLocalTimeStart_toString() {
        return String.valueOf(taskTimeStart);
    }

    /**
     * Return end time of LocalTime of a task in String
     *
     * @return String that represents the start time of taskTime
     */
    public String getLocalTimeEnd_toString() {
        return String.valueOf(taskTimeEnd);
    }
}
