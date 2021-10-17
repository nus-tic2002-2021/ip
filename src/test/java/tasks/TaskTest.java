package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void constructor_withDescription_success(){
        Task t = new Task("do something");
        assertEquals("do something", t.description);
        assertEquals(false, t.isDone);
    }

    @Test
    public void getDescription_withValidTask_success(){
        Task t = new Task("read a book");
        assertEquals("read a book", t.getDescription());
    }

    @Test
    public void getStatusIcon_withValidTask_success(){
        Task t = new Task("read a book");
        assertEquals(" ", t.getStatusIcon());

        t.markAsDone();
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    public void isDone_withValidTask_success() {
        Task t = new Task("revise TIC2002");
        assertEquals(false, t.isDone());

        t.markAsDone();
        assertEquals(true, t.isDone());
    }

    @Test
    public void markAsDone_withValidTask_success() {
        Task t = new Task("revise TIC2002");
        assertEquals(false, t.isDone);

        t.markAsDone();
        assertEquals(true, t.isDone);
    }

    @Test
    public void toString_withValidTask_success() {
        Task t = new Task("revise TIC2002");
        String expected = "[ ] revise TIC2002";
        assertEquals(expected, t.toString());
    }
}
