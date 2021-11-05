package duke.exceptiontest;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionTest {
    @Test
    public void exception(){
        LocalDateTime sample = LocalDateTime.of(2021,12,25,16,0,0);
        Deadline deadline = new Deadline("return book", sample);
        assertEquals("[D][ ] return book(by: 2021-12-25[SATURDAY] 16:00)", deadline.toString());
        assertEquals("D | 0 | return book | 2021-12-25 | 16:00", deadline.encodeTask());


    }
}
