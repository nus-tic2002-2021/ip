package Duke.Models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event Test1 = new Event("Task 1", "School");
    Event Test2 = new Event("Task 2", LocalDate.parse("2020-06-15"));
    Event Test3 = new Event("Task 3",
            LocalDate.parse("2021-11-14"), LocalTime.parse("23:59"));

    @Test
    public void getAdditionalInfoTest() {
        assertEquals("School", Test1.getAdditionalInfo());
        assertEquals("2020-06-15", Test2.getAdditionalInfo());
        assertEquals("2021-11-14 23:59", Test3.getAdditionalInfo());
    }

    @Test
    public void isCompletedTest() {
        assertEquals(false, Test1.isCompleted());
        assertEquals(false, Test2.isCompleted());
        assertEquals(false, Test3.isCompleted());
        Test2.markCompleted();
        Test3.markCompleted();
        assertEquals(true, Test2.isCompleted());
        assertEquals(true, Test3.isCompleted());
    }

    @Test
    public void getTaskTypeTest(){
        assertEquals("E", Test1.getTaskType());
        assertEquals("E", Test2.getTaskType());
        assertEquals("E", Test3.getTaskType());
    }

    @Test
    public void getCompletedSymbolTest() {
        assertEquals("[E][ ]", Test1.getCompletedSymbol());
        assertEquals("[E][ ]", Test2.getCompletedSymbol());
        assertEquals("[E][ ]", Test3.getCompletedSymbol());
        Test1.markCompleted();
        Test3.markCompleted();
        assertEquals("[E][X]", Test1.getCompletedSymbol());
        assertEquals("[E][X]", Test3.getCompletedSymbol());
    }

    @Test
    public void setPriorityTest() {
        Test1.setPriority("3");
        Test2.setPriority("1");
        Test3.setPriority("MEDIUM");
        assertEquals("HIGH", Test1.getPriority().toString());
        assertEquals("LOW", Test2.getPriority().toString());
        assertEquals("MEDIUM", Test3.getPriority().toString());
    }

    @Test
    public void getTaskInfoTest() {
        assertEquals("[E][ ] Task 1 (at: School) " +
                "- with NIL priority", Test1.getTaskInfo());
        Test1.setPriority("2");
        Test2.setPriority("testing");
        Test3.setPriority("HIGH");
        assertEquals("[E][ ] Task 1 (at: School) " +
                "- with MEDIUM priority", Test1.getTaskInfo());
        assertEquals("[E][ ] Task 2 (at: Jun 15 2020) " +
                "- with NIL priority", Test2.getTaskInfo());
        assertEquals("[E][ ] Task 3 (at: Nov 14 2021 23:59) " +
                "- with HIGH priority", Test3.getTaskInfo());
        Test1.markCompleted();
        assertEquals("[E][X] Task 1 (at: School) " +
                "- with MEDIUM priority", Test1.getTaskInfo());
    }
}

