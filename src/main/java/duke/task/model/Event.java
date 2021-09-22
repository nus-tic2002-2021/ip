package duke.task.model;


import duke.task.model.Task;



public final class Event extends Task {
    private String _from;
    private String _to;


    public Event(String taskDescription, String from, String to, Integer taskId, Boolean done) {
        super(taskDescription, taskId, done);
        this.setFrom(from);
        this.setTo(to);
    }

    private Event() {
    }

    public String getTo() {
        return this._to;
    }

    public String getFrom() {
        return this._from;
    }

    private void setFrom(String from) {
        this._from = from;
    }

    private void setTo(String to) {
        this._to = to;
    }

    public String getChronologyString() {
        return String.format("From: %s, To: %s", this.getFrom(), this.getTo());
    }

}
