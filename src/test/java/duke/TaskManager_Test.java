package duke;

import duke.task.model.Event;
import duke.testHelper.TestStream;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


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
    public void Test_AddEvents(){
        TaskManager tm = new TaskManager();

        String desc0 = "desc0";
        String desc1 = "desc1";
        tm.addNewEvent(desc0,"from","to");

        String from1 = "from1";
        String to1 = "to1";
        tm.addNewEvent(desc1,from1,to1);
        assertEquals(desc0, tm.getTaskById(0).getTaskDescription());
        assertEquals(desc1, tm.getTaskById(1).getTaskDescription());

        assertEquals(from1, ((Event)tm.getTaskById(1)).getFrom());
        assertEquals(to1, ((Event)tm.getTaskById(1)).getTo());
    }

}
