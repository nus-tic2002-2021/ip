package tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SchedulerTest {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    public void scheduleDeadline_validInputs_success() {
        Scheduler scheduler = new Scheduler();
        DeadlineTask deadlineTask = new DeadlineTask("deadline 1",
                LocalDateTime.parse("2021-10-18 0000", formatter));
        scheduler.scheduleDeadline(deadlineTask);
        ArrayList<Task> scheduledEvents = scheduler.getSchedule(LocalDate.parse("2021-10-18", dateFormatter));
        assertEquals(1, scheduledEvents.size());
    }

    @Test
    public void scheduleEvent_validInputs_success() {
        Scheduler scheduler = new Scheduler();
        EventTask eventTask = new EventTask("event A", LocalDateTime.parse("2021-10-18 1200", formatter),
                LocalDateTime.parse("2021-10-18 1300", formatter));
        boolean isScheduled = scheduler.scheduleEvent(eventTask);
        assertEquals(true, isScheduled);
        ArrayList<Task> scheduledEvents = scheduler.getSchedule(LocalDate.parse("2021-10-18", dateFormatter));
        assertEquals(1, scheduledEvents.size());
    }

    @Test
    public void scheduleEvent_validInputs_eventClash() {
        Scheduler scheduler = new Scheduler();
        EventTask eventTask1 = new EventTask("event A", LocalDateTime.parse("2021-10-18 1200", formatter),
                LocalDateTime.parse("2021-10-18 1300", formatter));
        EventTask eventTask2 = new EventTask("event A", LocalDateTime.parse("2021-10-18 1230", formatter),
                LocalDateTime.parse("2021-10-18 1330", formatter));
        boolean isScheduled1 = scheduler.scheduleEvent(eventTask1);
        boolean isScheduled2 = scheduler.scheduleEvent(eventTask2);
        assertEquals(true, isScheduled1);
        assertEquals(false, isScheduled2);
        ArrayList<Task> scheduledEvents = scheduler.getSchedule(LocalDate.parse("2021-10-18", dateFormatter));
        assertEquals(1, scheduledEvents.size());
    }

    @Test
    public void freeUpDeadlineSlot_validSlot_success() {
        Scheduler scheduler = new Scheduler();
        DeadlineTask deadlineTask = new DeadlineTask("deadline 1",
                LocalDateTime.parse("2021-10-18 1200", formatter));
        scheduler.scheduleDeadline(deadlineTask);
        ArrayList<Task> scheduledEvents = scheduler.getSchedule(LocalDate.parse("2021-10-18", dateFormatter));
        assertEquals(1, scheduledEvents.size());
        scheduler.freeUpDeadlineSlot(deadlineTask);
        ArrayList<Task> updatedScheduledEvents = scheduler.getSchedule(LocalDate.parse("2021-10-18", dateFormatter));
        assertEquals(0, updatedScheduledEvents.size());
    }

    @Test
    public void freeUpEventSlot_validSlot_success() {
        Scheduler scheduler = new Scheduler();
        EventTask eventTask = new EventTask("event A", LocalDateTime.parse("2021-10-18 1200", formatter),
                LocalDateTime.parse("2021-10-18 1300", formatter));
        boolean isScheduled = scheduler.scheduleEvent(eventTask);
        assertEquals(true, isScheduled);
        ArrayList<Task> scheduledEvents = scheduler.getSchedule(LocalDate.parse("2021-10-18", dateFormatter));
        assertEquals(1, scheduledEvents.size());
        scheduler.freeUpEventSlot(eventTask);
        ArrayList<Task> updatedScheduledEvents = scheduler.getSchedule(LocalDate.parse("2021-10-18", dateFormatter));
        assertEquals(0, updatedScheduledEvents.size());
    }

    @Test
    public void getSchedule_validDate_success() {
        Scheduler scheduler = new Scheduler();

        DeadlineTask deadlineTask1 = new DeadlineTask("deadline 1",
                LocalDateTime.parse("2021-10-18 0000", formatter));
        DeadlineTask deadlineTask2 = new DeadlineTask("deadline 1",
                LocalDateTime.parse("2021-10-19 0000", formatter));
        EventTask eventTask1 = new EventTask("event A", LocalDateTime.parse("2021-10-18 1200", formatter),
                LocalDateTime.parse("2021-10-18 1300", formatter));
        EventTask eventTask2 = new EventTask("event A", LocalDateTime.parse("2021-10-18 1300", formatter),
                LocalDateTime.parse("2021-10-18 1400", formatter));

        scheduler.scheduleDeadline(deadlineTask1);
        scheduler.scheduleDeadline(deadlineTask2);
        scheduler.scheduleEvent(eventTask1);
        scheduler.scheduleEvent(eventTask2);

        ArrayList<Task> scheduledEvents = scheduler.getSchedule(LocalDate.parse("2021-10-18", dateFormatter));
        assertEquals(3, scheduledEvents.size());
    }
}
