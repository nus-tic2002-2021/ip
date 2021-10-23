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
}
