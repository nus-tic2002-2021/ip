package task;

import exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void constructor_noDate_exceptionThrown(){
        try{
            Event event = new Event("Generate Exception /at");
            fail();
        }catch (DukeException e){
            String expectedOutput = "Incorrect event format. \nPlease key in event (details) /at d/mm/yyyy (start time)HHmm-(end time)HHmm";
            assertEquals(expectedOutput, e.getMessage());
        }
    }
}