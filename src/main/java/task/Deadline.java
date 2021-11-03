package task;

import datetime.DateTime;
import exception.DukeException;

/**
 * Deadline task object. Has a task name, date and time.
 */
public class Deadline extends Task {
    DateTime dateTime;

    /**
     * Constructor for Deadline object. Calls when generating TaskList based on user input.
     * @param description Includes task name, date and time.
     * @throws DukeException Throws when description format is incorrect. or when setDeadline throws DukeException.
     */
    public Deadline(String description) throws DukeException {
        super(description);
        int divider = description.indexOf("/by");
        if (divider == -1 || (divider == description.length() - 3)
                || description.substring(divider + 4).replace(" ", "").equals("")) {
            throw new DukeException("Incorrect deadline format.\nPlease key in deadline (task) "
                    + "/by (date)d/mm/yyyy (time)HHmm.");
        }
        dateTime = DateTime.setDeadline(description.substring(divider + 4, description.length()));
        super.taskDesc = super.taskDesc.substring(0,divider);
    }

    /**
     * toString method of DeadLine.
     * @return String denoting task name, status date and time of Deadline task.
     */
    @Override
    public String toString() {
        String output = "[D][" + super.getStatus() + "]" + " " + super.taskDesc + "(by: " + dateTime.toString() + ")";
        return output;
    }
}
