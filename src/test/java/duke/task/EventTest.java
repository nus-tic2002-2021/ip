package duke.task;

import duke.testhelper.help.CodeUnderTest.ParserUnderTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class EventTest {
    @Test
    void CreateEventTest(){
        String desc = "event1";
        String fromString = "nonsense day1";
        String toString = "asdasdasd asdasd ";
        int id = 1;
        boolean done = false;
        assertThrows(Exception.class, () -> ParserUnderTest.parseStringAsLocalDateTime(fromString));
        assertThrows(Exception.class, () -> ParserUnderTest.parseStringAsLocalDateTime(toString));
    }

}
