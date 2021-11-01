package tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SchedulerTest {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    @Test
    public void scheduleEvent_validInputs_success() {
        Scheduler scheduler = new Scheduler();
        EventTask eventTask = new EventTask("event A", LocalDateTime.parse("2021-10-18 1200", formatter),
                LocalDateTime.parse("2021-10-18 1300", formatter));
        boolean isScheduled = scheduler.scheduleEvent(eventTask);
        assertEquals(true, isScheduled);
    }

    @Test
    public void freeUpEventSlot_validSlot_success() {

    }

    @Test
    public void loadSchedule_validSchedule_success() {

    }

    @Test
    public void getSchedule_validDate_success() {

    }
}
