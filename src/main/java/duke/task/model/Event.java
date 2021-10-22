package duke.task.model;

import static duke.dukeUtility.parser.DateParser.prettifyLocalDateTime;
import java.time.LocalDateTime;

public final class Event extends Task {
    private LocalDateTime _from;
    private LocalDateTime _to;

    public Event(String taskDescription, LocalDateTime from, LocalDateTime to, Integer taskId, Boolean done) {
        super(taskDescription, taskId, done);
        this.setFrom(from);
        this.setTo(to);
    }

    private Event() {
    }

    public LocalDateTime getTo() {
        return this._to;
    }

    public LocalDateTime getFrom() {
        return this._from;
    }

    private void setFrom(LocalDateTime from) {
        this._from = from;
    }

    private void setTo(LocalDateTime to) {
        this._to = to;
    }

    public String getChronologyString() {

        return String.format("From: %s, To: %s", prettifyLocalDateTime(this.getFrom()),
            prettifyLocalDateTime(this.getTo()));

    }

}
