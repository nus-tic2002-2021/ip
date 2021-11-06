package task;

import error.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class EventTest {
    private List taskTest;
    public String error;
    @Test
    /**
     *  Test for deadline constructor
     */
    public void eventConstructor() {
        Event test = new Event("test description", LocalDateTime.of(2021,12,10,13,10), LocalTime.of(10,30));
        assertEquals("test description", test.getDescription());
        assertEquals(LocalDateTime.of(2021,12,10,13,10),test.getDateTime());
        assertEquals(LocalTime.of(10,30),test.getTime());
    }

    @Test
    /**
     *  Test for adding different types of task.
     */
    public void failTestCases() {
        taskTest = new List();
        try{
            taskTest.addTask("event","Concert /at 2020" );
        } catch (DukeException e){
            error = e.getMessage();
        }
        assertEquals("INVALID_DATE_FORMAT", error);

        try{
            taskTest.addTask("event","Concert /at" );
        } catch (DukeException e){
            error = e.getMessage();
        }
        assertEquals("EVENT_DESCRIPTION_ERROR", error);

        try{
            taskTest.addTask("event","Concert /at 2020-10-21 1300 /for 1" );
        } catch (DukeException e){
            error = e.getMessage();
        }
        assertEquals("INVALID_TIME_FORMAT", error);

        try{
            taskTest.addTask("event","Concert /at 2020-10-21 1300 /for 01:30 /for 02:30" );
        } catch (DukeException e){
            error = e.getMessage();
        }
        assertEquals("EVENT_LENGTH_ERROR", error);
    }
    @Test
    public void anomalyTest() {
        taskTest = new List();
        try{
            taskTest.addTask("event","Concert /at 2020-10-21 1300 /for 01:30" ); //first event
            taskTest.addTask("event","Concert /at 2020-10-21 1300 /for 01:30" ); //same time
            taskTest.addTask("event","Concert /at 2020-10-21 1330 /for 00:30" ); //during event
            taskTest.addTask("event","Concert /at 2020-10-20 2300 /for 19:30" ); //next day
        } catch (DukeException e){
            fail("Exception found");
        }
        assertEquals(1,taskTest.getArraySize());
    }
}
