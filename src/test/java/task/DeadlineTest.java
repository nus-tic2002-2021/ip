package task;

import error.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {
    private List taskTest;
    public String error;
    @Test
    /**
     *  Test for deadline constructor
     */
    public void deadlineConstructor() {
        Deadline test = new Deadline("test description", LocalDateTime.of(2021,12,10,13,10));
        assertEquals("test description", test.getDescription());
        assertEquals(LocalDateTime.of(2021,12,10,13,10),test.getDateTime());
    }
    @Test
    /**
     *  Test for adding deadline of task that fails.
     */
    public void failTestCases() {
        taskTest = new List();
        try{
            taskTest.addTask("deadline","Borrow book /by 2020" );
        } catch (DukeException e){
            error = e.getMessage();
        }
        assertEquals("INVALID_DATE_FORMAT", error);
        try{
            taskTest.addTask("deadline","Borrow book /by" );
        } catch (DukeException e){
            error = e.getMessage();
        }
        assertEquals("DEADLINE_DESCRIPTION_ERROR", error);

    }

}
