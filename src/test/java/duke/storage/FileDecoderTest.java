package duke.storage;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileDecoderTest {
    List<String> beforeTasks;
    List<Task> afterTasks;

    @BeforeEach
    void setUp() {
        beforeTasks = new ArrayList<>();
        afterTasks = new ArrayList<>();
    }

    @Test
    void decodeTasks_success() {
        beforeTasks.add("TODO | 1 | read");
        beforeTasks.add("DEADLINE | 0 | discussion  | 2021-11-17 12:00");

        Task task1 = new Todo("read");
        task1.setDone(true);
        afterTasks.add(task1);
        afterTasks.add(new Deadline("discussion", "2021-11-17 12:00"));

        assertEquals(afterTasks, FileDecoder.decodeTasks(beforeTasks));
    }

    @Test
    void decodeTasks_withTag_success() {
        beforeTasks.add("TODO | 0 | read | #fun");
        beforeTasks.add("DEADLINE | 0 | discussion  | 2021-11-17 12:00");

        Task task1 = new Todo("read");
        task1.setTag("fun");
        afterTasks.add(task1);
        afterTasks.add(new Deadline("discussion", "2021-11-17 12:00"));

        assertEquals(afterTasks, FileDecoder.decodeTasks(beforeTasks));
    }

    @Test
    void decodeTasks_noDescription_fail() {
        beforeTasks.add("DEADLINE | 0 |  | 2021-11-11 12:00");
        assertEquals(true, FileDecoder.decodeTasks(beforeTasks).isEmpty());
    }

    @Test
    void decodeTasks_invalidStatus_fail() {
        beforeTasks.add("DEADLINE | 5 | homework | 2021-11-11 12:00");
        assertEquals(true, FileDecoder.decodeTasks(beforeTasks).isEmpty());
    }

    @Test
    void decodeTasks_invalidDate_fail() {
        beforeTasks.add("DEADLINE | 5 | homework | 2021- 12:00");
        assertEquals(true, FileDecoder.decodeTasks(beforeTasks).isEmpty());
    }
}