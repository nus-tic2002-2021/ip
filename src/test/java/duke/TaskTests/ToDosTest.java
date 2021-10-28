package duke.TaskTests;

import duke.task.ToDos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void todo(){
        ToDos toDos = new ToDos("eat");
        assertEquals("[T][ ] eat", toDos.toString());
        assertEquals("T | 0 | eat", toDos.encodeTask());
    }
}
