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
    public void setPriorityTest() {
        Test1.setPriority("2");
        Test2.setPriority("random");
        Test3.setPriority("HIGH");
        assertEquals("MEDIUM", Test1.getPriority().toString());
        assertEquals("NIL", Test2.getPriority().toString());
        assertEquals("HIGH", Test3.getPriority().toString());
    }

    @Test
    public void getTaskInfoTest() {
        assertEquals("[T][ ] Task 1 " +
                "- with NIL priority", Test1.getTaskInfo());
        Test1.setPriority("HIGH");
        Test2.setPriority("0");
        Test3.setPriority("LOW");
        assertEquals("[T][ ] Task 1 " +
                "- with HIGH priority", Test1.getTaskInfo());
        assertEquals("[T][ ] Task 2 " +
                "- with NIL priority", Test2.getTaskInfo());
        assertEquals("[T][ ] Task 3 " +
                "- with LOW priority", Test3.getTaskInfo());

        Test3.markCompleted();
        Test3.setPriority("");
        assertEquals("[T][X] Task 3 " +
                "- with NIL priority", Test3.getTaskInfo());
    }
}
