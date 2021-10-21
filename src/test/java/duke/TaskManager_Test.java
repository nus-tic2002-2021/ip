package duke;

import duke.task.model.Event;
import duke.testHelper.TestStream;
import duke.testHelper.help.ParserUnderTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskManager_Test extends TestStream {

    @Test
    public void Test_AddToDos(){
        TaskManager tm = new TaskManager();

        String desc0 = "desc0";
        String desc1 = "desc1";
        tm.addNewToDo(desc0);
        tm.addNewToDo(desc1);
        assertEquals(desc0, tm.getTaskById(0).getTaskDescription());
        assertEquals(desc1, tm.getTaskById(1).getTaskDescription());
    }


    @Test
    public void Test_AddEvents() throws Exception{
        TaskManager tm = new TaskManager();

        String desc0 = "desc0";
        String desc1 = "desc1";
        tm.addNewEvent(desc0,ParserUnderTest.parseStringAsLocalDateTime("20210101"),"to");

        LocalDateTime from1 = ParserUnderTest.parseStringAsLocalDateTime("20210101");
        String to1 = "to1";
        tm.addNewEvent(desc1,from1,to1);

        assertEquals(desc0, tm.getTaskById(0).getTaskDescription());
        assertEquals(desc1, tm.getTaskById(1).getTaskDescription());

        assertEquals(from1, ((Event)tm.getTaskById(1)).getFrom());
        assertEquals(to1, ((Event)tm.getTaskById(1)).getTo());
    }

}
