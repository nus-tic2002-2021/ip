package duke.task;

import java.time.LocalDate;

import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Task;

public class TaskComparator {

    public static Boolean isSameDay(Task t, LocalDate targetDay) {
        LocalDate tDate = null;
        if (t instanceof Deadline) {
            tDate = ((Deadline) t).getDeadline().toLocalDate();
        } else if (t instanceof Event) {
            tDate = ((Event) t).getFrom().toLocalDate();
        }
        return targetDay.equals(tDate);
    }


    public static LocalDate getStartDate(Task t) {
        LocalDate tDate = null;
        if (t instanceof Deadline) {
            tDate = ((Deadline) t).getDeadline().toLocalDate();
        } else if (t instanceof Event) {
            tDate = ((Event) t).getFrom().toLocalDate();
        }
        return tDate;
    }

    public static Boolean isTaskWithinNextDays(Task t, Integer period) {
        LocalDate today = LocalDate.now();
        LocalDate finalDay = today.plusDays(period);
        LocalDate tDate = getStartDate(t);
        if (tDate == null) {
            return false;
        }
        return !(tDate.isBefore(today) || tDate.isAfter(finalDay));
    }

    public static int compareTaskDate(Task a, Task b) {
        LocalDate aDate = getStartDate(a);
        LocalDate bDate = getStartDate(b);

        if (aDate == null) {
            aDate = LocalDate.MIN;
        }
        if (bDate == null) {
            bDate = LocalDate.MIN;
        }
        if (aDate.isBefore(bDate)) {
            return -1;
        } else if (bDate.isAfter(aDate)) {
            return 1;
        } else {
            return 0;
        }
    }
}
