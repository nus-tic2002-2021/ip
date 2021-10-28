package duke.TaskTests;

import duke.task.Events;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void event(){
        LocalDateTime sample = LocalDateTime.of(2021,12,25,16,0,0);
        Events event = new Events("Finish Homework",sample.toLocalDate(),sample.toLocalTime(),sample.toLocalTime().plusHours(2));
        assertEquals("[E][ ] Finish Homework (on:2021-12-25[SATURDAY]     from:16:00    to:18:00)", event.toString());
        assertEquals("E | 0 | Finish Homework | 2021-12-25 | 16:00 | 18:00", event.encodeTask());
    }
}
