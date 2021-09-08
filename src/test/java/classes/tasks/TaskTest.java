package classes.tasks;

import classes.enums.TaskType;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    void testGetDescription() {
        Task task = new Task(TaskType.TASK, "read book");
        assert task.getDescription().equalsIgnoreCase("read book");
    }
}
