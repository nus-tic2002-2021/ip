package duke.unittest.tasktest.aggregator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import duke.task.aggregator.TaskList;
import duke.task.model.ToDo;
import duke.testhelper.TestStream;

public class TaskListTest extends TestStream {
    /**
     * Adds tasks to task manager.
     */
    @Test
    public void addToDos() {

        TaskList tl = new TaskList();
        String task0Description = "task 0";
        tl.addTask(new ToDo(task0Description, 0, false));
        assertEquals(task0Description, tl.getTaskById(0).getTaskDescription());
        String task1Description = "task 1";
        tl.addTask(new ToDo(task1Description, 1, false));
        assertEquals(task1Description, tl.getTaskById(1).getTaskDescription());
        assertTrue(tl.containsKey(0));
        assertTrue(tl.containsKey(1));
        assertFalse(tl.containsKey(2));
    }
}
