package java.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Create an instance of deadline class
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */
public class TaskDeadline extends Task {
    private LocalDate taskDate;
    private LocalTime taskTime;

    /**
     * Constructor
     */
    public TaskDeadline(String taskDetail, LocalDate taskDate) {
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.DEADLINE;
        this.taskDate = taskDate;
    }

    /**
     * Constructor
     */
    public TaskDeadline(String taskDetail, LocalDate taskDate, LocalTime localTime) {
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.DEADLINE;
        this.taskDate = taskDate;
        this.taskTime = localTime;
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
    public String getLocalDateToString() {
        return String.valueOf(taskDate);
    }

    /**
     * Return day from LocalDate of a task in String
     *
     * @return String that represents the day of taskDate
     */
    public String getLocalDateDayToString() {
        return String.valueOf(taskDate.getDayOfMonth());
    }

    /**
     * Return month from LocalDate of a task in String
     *
     * @return String that represents the month of taskDate
     */
    public String getLocalDateMonthToString() {
        return String.valueOf(taskDate.getMonth());
    }

    /**
     * Return year from LocalDate of a task in String
     *
     * @return String that represents the year of taskDate
     */
    public String getLocalDateYearToString() {
        return String.valueOf(taskDate.getYear());
    }

    /**
     * Return LocalTime of a task
     *
     * @return LocalTime that represents the taskTime
     */
    public LocalTime getLocalTime() {
        return taskTime;
    }

    /**
     * Return LocalTime of a task in String
     *
     * @return String that represents the taskTime
     */
    public String getLocalTime_toString() {
        return String.valueOf(taskTime);
    }
}
