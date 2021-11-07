package task;

import error.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {
    private List taskTest;
    public String error;

    @Test
    /**
     *  Test for deadline constructor
     */
    public void eventConstructor() {
        Event test = new Event("test description", LocalDateTime.of(2021, 12, 10, 13, 10), LocalTime.of(10, 30));
        assertEquals("test description", test.getDescription());
        assertEquals(LocalDateTime.of(2021, 12, 10, 13, 10), test.getDateTime());
        assertEquals(LocalTime.of(10, 30), test.getTime());
    }

}
