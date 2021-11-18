package com.tic2002.task;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.tic2002.app.DateTime.*;


/**
 * Deadline is inheriting from Task
 */
public class Deadline extends Task {
    /**
     * Deadline takes in input of String description, LocalDate dDate and LocalTime dTime
     * @param description description from Parser that user entered
     * @param dDate date from Parser
     * @param dTime time from Parser
     * The Deadline method stores these tasks in Task
     * super.type - Assigns type "D"
     * super.isDone - Assigns false to done
     * @Override - print task in deadline format
     */
    public Deadline(String description, LocalDate dDate, LocalTime dTime) {
        super(description);
        super.type("D");
        super.date = dDate;
        super.time = dTime;
        super.isDone = false;
        super.priority = 0;
        if(isWithinThreeDays(dDate)){
            super.priority = 3;
        }
        else if(isOverDue(dDate)){
            super.priority = 4;
        }
        else{
            super.priority = 0;
        }
    }

    @Override
    public String printTask() {
        return super.printTask() + " (by: " + toNewDateFormat(super.date) + ", " + super.time + ")";
    }
}
