package duke.tasklist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void constructTaskTest() {
        Task t = new Task("something");
        assertEquals("something", t.description);
        assertEquals(false, t.isDone);
    }

    @Test
    public void getDescriptionTest() {
        Task t = new Task("develop duke chatbot");
        assertEquals("develop duke chatbot", t.getDescription());
    }

}
