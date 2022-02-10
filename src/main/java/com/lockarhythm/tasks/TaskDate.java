package com.lockarhythm.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.ocpsoft.prettytime.nlp.PrettyTimeParser;

class TaskDate implements Serializable {
  private LocalDateTime d;

  public TaskDate(String s) {
    List<Date> dates = new PrettyTimeParser().parse(s);
    if (dates.size() > 0) {
      d = toLocalDateTime(dates.get(0));
      return;
    }
    d = LocalDateTime.parse(s);
  }

  public static int compare(TaskDate x, TaskDate y) {
    return x.d.compareTo(y.d);
  }

  @Override
  public String toString() {
    return d.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
  }

  private LocalDateTime toLocalDateTime(Date dateToConvert) {
    return LocalDateTime.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault());
  }
}
