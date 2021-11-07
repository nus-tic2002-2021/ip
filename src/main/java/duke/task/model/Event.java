package duke.task.model;

import static duke.dukeutility.parser.DateParser.prettifyLocalDateTime;

import java.time.LocalDateTime;

public final class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * An event.
     *
     * @param taskDescription
     * @param from
     * @param to
     * @param taskId
     * @param done
     */
    public Event(String taskDescription, LocalDateTime from, LocalDateTime to, Integer taskId, Boolean done) {
        super(taskDescription, taskId, done);
        this.setFrom(from);
        this.setTo(to);
    }

    private Event() {
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    private void setFrom(LocalDateTime from) {
        this.from = from;
    }

    private void setTo(LocalDateTime to) {
        this.to = to;
    }

    public String getChronologyString() {

        return String.format("From: %s, To: %s", prettifyLocalDateTime(this.getFrom()),
            prettifyLocalDateTime(this.getTo()));

    }

}
