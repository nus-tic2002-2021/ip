package task;

import java.time.LocalDate;
import java.time.LocalTime;

import static app.DateTime.toNewDateFormat;
import static task.Priority.printPriority;

/**
 * Deadline is inheriting from Task
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Deadline takes in input of String description, LocalDate dDate and LocalTime dTime
     * @param description description from Parser that user entered
     * @param dDate date from Parser
     * @param dTime time from Parser
     * The Deadline method stores these tasks in Task
     * super.type - Assigns type "D"
     * super.isDone - Assigns false to done
     */
    public Deadline(String description, LocalDate dDate, LocalTime dTime) {
        super(description);
        super.type("D");
        super.date = dDate;
        super.time = dTime;
        super.isDone = false;
        super.priority = 0;
    }

    @Override
/**
 * Print Task that has been added to ArrayList
 */
    public String printTask() {
        return super.printTask() + " (by: " + toNewDateFormat(super.date) + ", " + super.time + ")";
    }
}
