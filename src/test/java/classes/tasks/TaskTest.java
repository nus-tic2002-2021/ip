package classes.tasks;

import classes.enums.TaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    void getDescriptionSuccess() {
        Task task = new Task(TaskType.TASK, "read book");
        Assertions.assertEquals("read book", task.getDescription());
    }
}
