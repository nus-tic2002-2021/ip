package duke.task.model;

import static duke.dukeutility.parser.DateParser.prettifyLocalDateTime;

import java.time.LocalDateTime;

public final class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * A deadline
     * @param taskDescription
     * @param deadline
     * @param taskId
     * @param done
     */
    public Deadline(String taskDescription, LocalDateTime deadline, Integer taskId, Boolean done) {
        super(taskDescription, taskId, done);
        this.setDeadline(deadline);
    }

    private Deadline() {
    }

    private void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }


    public String getChronologyString() {
        return "By: " + prettifyLocalDateTime(this.getDeadline());
    }
}
