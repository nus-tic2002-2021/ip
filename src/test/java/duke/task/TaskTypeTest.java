package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskTypeTest {

    @Test
    void testTaskTypeToStringtaskTypeString() {
        List<TaskType> types = List.of(TaskType.TODOS, TaskType.DEADLINE, TaskType.EVENT);
        Assertions.assertAll(() ->
                assertEquals("T", TaskType.taskTypeToString(types.get(0))), () ->
                assertEquals("D", TaskType.taskTypeToString(types.get(1))), () ->
                assertEquals("E", TaskType.taskTypeToString(types.get(2))));
    }
}
