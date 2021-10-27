package duke.unittest.tasktest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.testhelper.help.codeundertest.ParserUnderTest;


public class EventTest {
    @Test
    void createEventTest_expectInvalidDateStringToThrow() {
        String desc = "event1";
        String fromString = "nonsense day1";
        String toString = "asdasdasd asdasd ";
        int id = 1;
        boolean done = false;
        assertThrows(Exception.class, () -> ParserUnderTest.parseStringAsLocalDateTime(fromString));
        assertThrows(Exception.class, () -> ParserUnderTest.parseStringAsLocalDateTime(toString));
    }

}
