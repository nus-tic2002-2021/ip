package duke.task.model;

import java.time.LocalDateTime;

public final class Event extends Task {
    private LocalDateTime _from;
    private String _to;

    public Event(String taskDescription, LocalDateTime from, String to, Integer taskId, Boolean done) {
        super(taskDescription, taskId, done);
        this.setFrom(from);
        this.setTo(to);
    }

    private Event() {
    }

    public String getTo() {
        return this._to;
    }

    public LocalDateTime getFrom() {
        return this._from;
    }

    private void setFrom(LocalDateTime from) {
        this._from = from;
    }

    private void setTo(String to) {
        this._to = to;
    }

    public String getChronologyString() {
        return String.format("From: %s, To: %s", this.getFrom(), this.getTo());
    }

}
