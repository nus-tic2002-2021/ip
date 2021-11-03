package task;

import exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void constructor_noDate_exceptionThrown() {
        try {
            Event event = new Event("Generate Exception /at");
            fail();
        } catch (DukeException e) {
            String expectedOutput = "Incorrect event format.\n"
                    + "Please key in event (details) /at (date)d/mm/yyyy (start time)HHmm-(end time)HHmm.";
            assertEquals(expectedOutput, e.getMessage());
        }
    }
}