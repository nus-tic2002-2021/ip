package command;

import error.DukeException;
import org.junit.jupiter.api.Test;
import task.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class AddCommandTest
{
    private List taskTest;
    public String error;

    @Test
    /**
     *  Test for adding deadline of task that fails.
     */

    public void failTestDeadlineCases() {
        taskTest = new List();
        try{
            taskTest.addDeadline("Borrow book /by 2020" );
        } catch (DukeException e){
            error = e.getMessage();
        }
        assertEquals("INVALID_DATETIME_FORMAT", error);
        try{
            taskTest.addDeadline("Borrow book /by" );
        } catch (DukeException e){
            error = e.getMessage();
        }
        assertEquals("DEADLINE_DESCRIPTION_ERROR", error);

    }


    @Test
    /**
     *  Test for adding different types of task.
     */
    public void failTestEventCases() {
        taskTest = new List();
        try{
            taskTest.addEvent("Concert /at 2020" );
        } catch (DukeException e){
            error = e.getMessage();
        }
        assertEquals("INVALID_DATETIME_FORMAT", error);

        try{
            taskTest.addEvent("Concert /at" );
        } catch (DukeException e){
            error = e.getMessage();
        }
        assertEquals("EVENT_DESCRIPTION_ERROR", error);

        try{
            taskTest.addEvent("Concert /at 2020-10-21 1300 /for 1" );
        } catch (DukeException e){
            error = e.getMessage();
        }
        assertEquals("INVALID_TIME_FORMAT", error);

        try{
            taskTest.addEvent("Concert /at 2020-10-21 1300 /for 01:30 /for 02:30" );
        } catch (DukeException e){
            error = e.getMessage();
        }
        assertEquals("EVENT_LENGTH_ERROR", error);
    }
    @Test
    public void anomalyTest() {
        taskTest = new List();
        try{
            taskTest.addEvent("Concert /at 2020-10-21 1300 /for 01:30" ); //first event
            taskTest.addEvent("Concert /at 2020-10-21 1300 /for 01:30" ); //same time
            taskTest.addEvent("Concert /at 2020-10-21 1330 /for 00:30" ); //during event
            taskTest.addEvent("Concert /at 2020-10-20 2300 /for 19:30" ); //next day
        } catch (DukeException e){
            fail("Exception found");
        }
        assertEquals(1,taskTest.getArraySize());
    }
}
