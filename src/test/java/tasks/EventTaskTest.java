package tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    @Test
    public void constructor_withDescriptionAndDate_success(){
        EventTask t = new EventTask("do something", LocalDateTime.parse("2021-10-18 1200", formatter),
                LocalDateTime.parse("2021-10-18 1300", formatter));
        assertEquals("do something", t.description);
        assertEquals(LocalDateTime.parse("2021-10-18 1200", formatter), t.start);
        assertEquals(LocalDateTime.parse("2021-10-18 1300", formatter), t.end);
    }

    @Test
    public void toString_withValidToDo_success() {
        EventTask t = new EventTask("do something", LocalDateTime.parse("2021-10-18 1200", formatter),
                LocalDateTime.parse("2021-10-18 1300", formatter));
        String expected = "[E][ ] do something (at: 18 Oct 2021, 12:00:00 PM - 18 Oct 2021, 13:00:00 PM)";
        assertEquals(expected, t.toString());
    }
}
