package task;

import error.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {

    @Test
    /**
     *  Test for deadline constructor
     */
    public void deadlineConstructor() {
        Deadline test = new Deadline("test description", LocalDateTime.of(2021,12,10,13,10));
        assertEquals("test description", test.getDescription());
        assertEquals(LocalDateTime.of(2021,12,10,13,10),test.getDateTime());
    }

}
