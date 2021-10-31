package tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {
    @Test
    public void constructor_withDescriptionAndDate_success() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DeadlineTask t = new DeadlineTask("do something", LocalDateTime.parse("2021-10-17 1200", formatter));
        assertEquals("do something", t.description);
        assertEquals(LocalDateTime.parse("2021-10-17 1200", formatter), t.deadline);
    }

    @Test
    public void toString_withValidToDo_success() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DeadlineTask t = new DeadlineTask("do something", LocalDateTime.parse("2021-10-17 1200", formatter));
        String expected = "[D][ ] do something (by: 17 Oct 2021, 12:00 PM)";
        assertEquals(expected, t.toString());
    }
}
