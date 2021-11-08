package Duke.Models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Deadline Test1 = new Deadline("Task 1", "tomorrow");
    Deadline Test2 = new Deadline("Task 2", LocalDate.parse("2010-10-19"));
    Deadline Test3 = new Deadline("Task 3",
            LocalDate.parse("2012-01-20"), LocalTime.parse("18:00"));

    @Test
    public void getAdditionalInfoTest() {
        assertEquals("tomorrow", Test1.getAdditionalInfo());
        assertEquals("2010-10-19", Test2.getAdditionalInfo());
        assertEquals("2012-01-20 18:00", Test3.getAdditionalInfo());
    }

    @Test
    public void isCompletedTest() {
        assertEquals(false, Test1.isCompleted());
        assertEquals(false, Test2.isCompleted());
        assertEquals(false, Test3.isCompleted());
        Test1.markCompleted();
        Test3.markCompleted();
        assertEquals(true, Test1.isCompleted());
        assertEquals(true, Test3.isCompleted());
    }

    @Test
    public void getTaskTypeTest(){
        assertEquals("D", Test1.getTaskType());
        assertEquals("D", Test2.getTaskType());
        assertEquals("D", Test3.getTaskType());
    }

    @Test
    public void getCompletedSymbolTest() {
        assertEquals("[D][ ]", Test1.getCompletedSymbol());
        assertEquals("[D][ ]", Test2.getCompletedSymbol());
        assertEquals("[D][ ]", Test3.getCompletedSymbol());
        Test1.markCompleted();
        Test2.markCompleted();
        assertEquals("[D][X]", Test1.getCompletedSymbol());
        assertEquals("[D][X]", Test2.getCompletedSymbol());
    }

    @Test
    public void getTaskInfoTest() {
        assertEquals("[D][ ] Task 1 (by: tomorrow)", Test1.getTaskInfo());
        assertEquals("[D][ ] Task 2 (by: Oct 19 2010)", Test2.getTaskInfo());
        assertEquals("[D][ ] Task 3 (by: Jan 20 2012 18:00)", Test3.getTaskInfo());
        Test2.markCompleted();
        assertEquals("[D][X] Task 2 (by: Oct 19 2010)", Test2.getTaskInfo());
    }
}
