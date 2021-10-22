package duke.task.model;

import static duke.dukeUtility.parser.DateParser.prettifyLocalDateTime;
import java.time.LocalDateTime;

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


    public String getChronologyString() {
        return "By: " + prettifyLocalDateTime(this.getDeadline());
    }
}
