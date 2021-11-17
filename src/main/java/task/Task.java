package task;

import java.time.LocalDate;
import java.time.LocalTime;

import static task.Priority.printPriority;

/**
 * Creates what is in a Task
 * creates String description - description of task
 * creates boolean isDone - if the task is done
 * creates String type - what type of task is it (Todo, Deadline, Event)
 * creates LocalDate date - stores date for Deadline
 * creates LocalTime time - stores time for Deadline
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String timing;
    protected LocalDate date;
    protected LocalTime time;
    protected int priority;

    public Task(){}

    /**
     * Modify basic Task
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Modify type of task
     * @param type type of task assigned
     */
    public void type(String type){
        this.type = type;
    }
    /**
     * Modify timing of Event
     * @param timing timing of Event
     */
    public void timing (String timing){
        this.timing = timing;
    }
    /**
     * Modify status of task
     * @param isDone if task is completed
     */
    public void isDone(Boolean isDone) {this.isDone = isDone; }

    public void priority (int priority) {this.priority = priority; }
    /**
     * Modify date of task to be completed by
     * @param date date of task to be completed by
     */
    public void date (LocalDate date){this.date = date; }
    /**
     * Modify time of task to be completed by
     * @param time date of task to be completed by
     */
    public void time (LocalTime time){this.time = time; }

    /**
     * Method to set task as done
     */
    public void setDone() {
        this.isDone = true;
    }

    public void setUnDone() {this.isDone = false; }


    /**
     * Method to get status of isDone
     * @return "X" if true, "" if false
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Retrieve done status of isDone
     * @return true or false
     */
    public String getDone() { return (isDone ? "true" : "false");}

    /**
     * Retrieve description
     * @return description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * retrieve type of Task
     * @return type of task
     */
    public String getType() { return type; }
    /**
     * retrieve Timing of Event
     * @return timing
     */
    public String getTiming() {return timing; }
    /**
     * retrieve Date of Deadline
     * @return date
     */
    public LocalDate getDate() { return date; }
    /**
     * retrieve Time of Deadline
     * @return time
     */
    public LocalTime getTime() { return time; }

    /**
     * Get priority number
     * @return priority number
     */
    public int getPriority() {return priority; }

    /**
     * prints tasks out
     * @return format of task with type, status and description of Task
     */
    public String printTask() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "]" + printPriority(priority) + " " + getDescription();
    }
}
