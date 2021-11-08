package Duke.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    Todo Test1 = new Todo("Task 1");
    Todo Test2 = new Todo("Task 2");
    Todo Test3 = new Todo("Task 3");

    @Test
    public void getTaskTypeTest(){
        assertEquals("T", Test1.getTaskType());
        assertEquals("T", Test2.getTaskType());
        assertEquals("T", Test3.getTaskType());
    }

    @Test
    public void getCompletedSymbolTest() {
        assertEquals("[T][ ]", Test1.getCompletedSymbol());
        assertEquals("[T][ ]", Test2.getCompletedSymbol());
        assertEquals("[T][ ]", Test3.getCompletedSymbol());
        Test2.markCompleted();
        Test3.markCompleted();
        assertEquals("[T][X]", Test2.getCompletedSymbol());
        assertEquals("[T][X]", Test3.getCompletedSymbol());
    }

    @Test
    public void getTaskInfoTest() {
        assertEquals("[T][ ] Task 1", Test1.getTaskInfo());
        assertEquals("[T][ ] Task 2", Test2.getTaskInfo());
        assertEquals("[T][ ] Task 3", Test3.getTaskInfo());
        Test3.markCompleted();
        assertEquals("[T][X] Task 3", Test3.getTaskInfo());
    }
}
