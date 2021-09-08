package classes.tasks;

import classes.enums.TaskType;

public class Event extends Task {
  private String timing;

  public Event(String description, String timing) {
    super(TaskType.EVENT, description);
    this.setTiming(timing);
  }

  public String getTiming() {
    return timing;
  }

  public void setTiming(String timing) {
    this.timing = timing;
  }

  @Override
  public String toStatusString() {
    return "[E]" + super.toStatusString() + " (at: " + timing + ")";
  }
}
