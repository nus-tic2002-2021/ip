package task;

import datetime.DateTime;
import exception.DukeException;

public class Event extends Task {
    DateTime dateTime;

    /**
     * Constructor for Event object. Calls when generating TaskList based on user input.
     * @param description Contains task name, date, starting time and ending time.
     * @throws DukeException Throws when description format is incorrect. or when setEventTime throws DukeException.
     */
    public Event(String description) throws DukeException {
        super(description);
        int divider = description.indexOf("/at");
        if (divider == -1 || (divider == description.length() - 3)
                || description.substring(divider + 4).replace(" ", "").equals("")) {
            throw new DukeException("Incorrect event format.\n"
                                + "Please key in event (details) /at (date)d/mm/yyyy (start time)HHmm-(end time)HHmm.");
        }
        dateTime = DateTime.setEventTime(description.substring(divider + 4, description.length()));
        super.taskDesc = super.taskDesc.substring(0,divider);
    }

    /**
     * toString method of Event.
     * @return String denoting task name, status, date, and starting and ending time of task.
     */
    @Override
    public String toString() {
        String output = "[E][" + super.getStatus() + "]" + " " + super.taskDesc
            + "(at: " + dateTime.toString() + ")";
        return output;
    }
}
