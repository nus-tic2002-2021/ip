package duke.task;

import duke.testHelper.help.ParserUnderTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class EventTest {
    @Test
    void CreateEventTest() throws Exception{
        String desc = "event1";
        String fromString = "nonsense day1";
        String toString = "day2";
        int id = 1;
        boolean done = false;

        Exception exception = assertThrows(Exception.class, () -> ParserUnderTest.parseStringAsLocalDateTime(fromString));
    }

}
