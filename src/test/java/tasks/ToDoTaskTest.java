package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {
    @Test
    public void constructor_withDescription_success() {
        ToDoTask t = new ToDoTask("do something");
        assertEquals("do something", t.description);
    }

    @Test
    public void toString_withValidToDo_success() {
        ToDoTask t = new ToDoTask("revise TIC2002");
        String expected = "[T][ ] revise TIC2002";
        assertEquals(expected, t.toString());
    }
}
