package datetime;

import exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {

    @Test
    public void setDeadLine_incorrectFormat_exceptionThrown() {
        try {
            DateTime.setDeadline("10.02.2021 15:00");
            fail();
        } catch (DukeException e) {
            assertEquals("Incorrect deadline datetime format.\n"
                    + "Please key in deadline (details) /by (date)d/mm/yyyy (time)HHmm.", e.getMessage());
        }
    }

    @Test
    public void readDeadLineTest() {
        DateTime deadline = DateTime.readDeadLine("30/11/2019 0500");
        assertEquals("30/11/2019 0500", deadline.toString());
    }

    @Test
    public void setEventTime_noEndTime_exceptionThrown() {
        try {
            DateTime.setEventTime("05/11/2021 0500-");
            fail();
        } catch (DukeException e) {
            assertEquals("Please key in the end time.", e.getMessage());
        }
    }

    @Test
    public void setEventTime_incorrectFormat_exceptionThrown() {
        try {
            DateTime.setEventTime("15.11.2021 05:00-05:30");
            fail();
        } catch (DukeException e) {
            assertEquals("Incorrect event format.\n"
                    + "Please key in event (details) /at (date)d/mm/yyyy "
                    + "(start time)HHmm-(end time)HHmm.", e.getMessage());
        }
    }

    @Test
    public void readEventTimeTest() {
        try {
            DateTime eventTime = DateTime.readEventTime("17/11/2019 0500-0600");
            assertEquals("17/11/2019 0500-0600", eventTime.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void readEventTime_noEndTime_exceptionThrown() {
        try {
            DateTime eventTime = DateTime.readEventTime("30/11/2021 0500-");
            fail();
        } catch (DukeException e) {
            assertEquals("Please key in the end time.", e.getMessage());
        }
    }
}