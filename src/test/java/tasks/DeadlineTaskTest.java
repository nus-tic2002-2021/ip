package tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {
    @Test
    public void constructor_withDescriptionAndDate_success(){
        DeadlineTask t = new DeadlineTask("do something", LocalDate.parse("2021-10-17"));
        assertEquals("do something", t.description);
        assertEquals(LocalDate.parse("2021-10-17"), t.by);
    }

    @Test
    public void toString_withValidToDo_success() {
        DeadlineTask t = new DeadlineTask("do something", LocalDate.parse("2021-10-17"));
        String expected = "[D][ ] do something (by: 17 Oct 2021)";
        assertEquals(expected, t.toString());
    }
}
