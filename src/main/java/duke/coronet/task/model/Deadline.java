package duke.coronet.task.model;


import java.time.LocalDateTime;

import static duke.coronet.dukeUtility.parser.DateParser.prettifyLocalDateTime;


public final class Deadline extends Task {
    private LocalDateTime _deadline;

    public Deadline(String taskDescription, LocalDateTime deadline, Integer taskId, Boolean done) {
        super(taskDescription, taskId, done);
        this.setDeadline(deadline);
    }


    private Deadline() {
    }

    private void setDeadline(LocalDateTime deadline) {
        this._deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this._deadline;
    }

    public static Deadline createExisting(String taskDescription, LocalDateTime deadline, Integer taskId, Boolean done) {
        return new Deadline(taskDescription, deadline, taskId, done);
    }

    public String getChronologyString() {
        return "By: " + prettifyLocalDateTime(this.getDeadline());
    }
}
