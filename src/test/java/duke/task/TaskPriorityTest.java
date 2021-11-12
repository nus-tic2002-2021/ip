package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskPriorityTest {

    @Test
    void testIsHigherPriority() {
        TaskPriority taskPriorityHigh = TaskPriority.HIGH;
        TaskPriority taskPriorityLow = TaskPriority.LOW;

        assertEquals(true, TaskPriority.isHigherPriority(taskPriorityHigh, taskPriorityLow));
    }

    @Test
    void testConvertIntToPriority() {
        List<Integer> priorities = List.of(1,2,3,4,100,0);

        Assertions.assertAll(
                () -> assertEquals(TaskPriority.HIGH, TaskPriority.convertIntToPriority(priorities.get(0))),
                () -> assertEquals(TaskPriority.MEDIUM, TaskPriority.convertIntToPriority(priorities.get(1))),
                () -> assertEquals(TaskPriority.LOW, TaskPriority.convertIntToPriority(priorities.get(2))),
                () -> assertEquals(TaskPriority.INVALID, TaskPriority.convertIntToPriority(priorities.get(3))),
                () -> assertEquals(TaskPriority.INVALID, TaskPriority.convertIntToPriority(priorities.get(4))),
                () -> assertEquals(TaskPriority.INVALID, TaskPriority.convertIntToPriority(priorities.get(5))));
    }

    @Test
    void testConvertStringToPriority() {
        List<String> priorities = List.of("1","2","3","4","100","0");

        Assertions.assertAll(
                () -> assertEquals(TaskPriority.HIGH, TaskPriority.convertStringToPriority(priorities.get(0))),
                () -> assertEquals(TaskPriority.MEDIUM, TaskPriority.convertStringToPriority(priorities.get(1))),
                () -> assertEquals(TaskPriority.LOW, TaskPriority.convertStringToPriority(priorities.get(2))),
                () -> assertEquals(TaskPriority.INVALID, TaskPriority.convertStringToPriority(priorities.get(3))),
                () -> assertEquals(TaskPriority.INVALID, TaskPriority.convertStringToPriority(priorities.get(4))),
                () -> assertEquals(TaskPriority.INVALID, TaskPriority.convertStringToPriority(priorities.get(5))));
    }

    @Test
    void testConvertPriorityToInt() {
        List<TaskPriority> priorities = List.of(TaskPriority.LOW,
                TaskPriority.MEDIUM, TaskPriority.HIGH, TaskPriority.INVALID);

        Assertions.assertAll(
                () -> assertEquals(3, TaskPriority.convertPriorityToInt(priorities.get(0))),
                () -> assertEquals(2, TaskPriority.convertPriorityToInt(priorities.get(1))),
                () -> assertEquals(1, TaskPriority.convertPriorityToInt(priorities.get(2))),
                () -> assertEquals(0, TaskPriority.convertPriorityToInt(priorities.get(3))));
    }

    @Test
    void testConvertPriorityToString() {
        List<TaskPriority> priorities = List.of(TaskPriority.LOW,
                TaskPriority.MEDIUM, TaskPriority.HIGH, TaskPriority.INVALID);

        Assertions.assertAll(
                () -> assertEquals("LOW", TaskPriority.convertPriorityToString(priorities.get(0))),
                () -> assertEquals("MEDIUM", TaskPriority.convertPriorityToString(priorities.get(1))),
                () -> assertEquals("HIGH", TaskPriority.convertPriorityToString(priorities.get(2))),
                () -> assertEquals("INVALID", TaskPriority.convertPriorityToString(priorities.get(3))));
    }

    @Test
    void testToStringInNumber() {

        Assertions.assertAll(
                () -> assertEquals("3", TaskPriority.LOW.toStringInNumber()),
                () -> assertEquals("2", TaskPriority.MEDIUM.toStringInNumber()),
                () -> assertEquals("1", TaskPriority.HIGH.toStringInNumber()),
                () -> assertEquals("0", TaskPriority.INVALID.toStringInNumber()));
    }
}