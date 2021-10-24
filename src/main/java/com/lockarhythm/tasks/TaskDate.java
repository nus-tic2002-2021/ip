package com.lockarhythm.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class TaskDate implements Serializable {
  private LocalDateTime d;

  public TaskDate(String s) {
    d = LocalDateTime.parse(s);
  }

  public static int compare(TaskDate x, TaskDate y) {
    return x.d.compareTo(y.d);
  }

  @Override
  public String toString() {
    return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
  }
}
