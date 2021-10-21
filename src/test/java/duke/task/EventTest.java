package duke.task;

import duke.task.model.Event;
import org.junit.jupiter.api.Test;


public class EventTest {
    @Test
    void CreateEventTest(){
        String desc = "event1";
        String fromString = "day1";
        String toString = "day2";
        int id = 1;
        boolean done = false;
        new Event(desc,fromString,toString,id,done);
    }

}
