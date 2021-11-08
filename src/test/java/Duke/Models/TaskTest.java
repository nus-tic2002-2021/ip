package Duke.Models;

import Duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task Test1 = new Task("Task 1");
    Task Test2 = new Task("Task 2");

    @Test
    public void getDescriptionTest() {
        assertEquals("Task 1", Test1.getDescription());
        assertEquals("Task 2", Test2.getDescription());
        try {
            Task Test3 = new Task("Task 3", "0");
            assertEquals("Task 3", Test3.getDescription());
        } catch (DukeException e) {
            System.out.println("Task creation failed");
        }
    }

    @Test
    public void completedTest() {
        assertEquals(false, Test1.isCompleted());
        assertEquals(false, Test2.isCompleted());

        Test2.markCompleted();
        assertEquals(true, Test2.isCompleted());

        try {
            Task Test3 = new Task("Task 3", "1");
            assertEquals(true, Test3.isCompleted());
        } catch (DukeException e) {
            System.out.println("Task creation failed");
        }

        try {
            Task Test4 = new Task("Task 4", "0");
            assertEquals(false, Test4.isCompleted());
            Test4.markCompleted();
            assertEquals(true, Test4.isCompleted());
        } catch (DukeException e) {
            System.out.println("Task creation failed");
        }
    }

    @Test
    public void getCompleteSymbolTest() {
        assertEquals("[ ]", Test1.getCompletedSymbol());
        assertEquals("[ ]", Test2.getCompletedSymbol());

        Test1.markCompleted();
        assertEquals("[X]", Test1.getCompletedSymbol());

        try {
            Task Test3 = new Task("Task 3", "0");
            assertEquals("[ ]", Test3.getCompletedSymbol());
            Test3.markCompleted();
            assertEquals("[X]", Test3.getCompletedSymbol());
        } catch (DukeException e) {
            System.out.println("Task creation failed");
        }

        try {
            Task Test4 = new Task("Task 4", "0");
            assertEquals("[ ]", Test4.getCompletedSymbol());
        } catch (DukeException e) {
            System.out.println("Task creation failed");
        }
    }

    @Test
    public void getTaskInfoTest() {
        assertEquals("[ ] Task 1", Test1.getTaskInfo());
        assertEquals("[ ] Task 2", Test2.getTaskInfo());
        Test2.markCompleted();
        assertEquals("[X] Task 2", Test2.getTaskInfo());

        try {
            Task Test3 = new Task("Task 3", "0");
            assertEquals("[ ] Task 3", Test3.getTaskInfo());
            Test3.markCompleted();
            assertEquals("[X] Task 3", Test3.getTaskInfo());
        } catch (DukeException e) {
            System.out.println("Task creation failed");
        }

        try {
            Task Test4 = new Task("Task 4", "1");
            assertEquals("[X] Task 4", Test4.getTaskInfo());
        } catch (DukeException e) {
            System.out.println("Task creation failed");
        }
    }
}
