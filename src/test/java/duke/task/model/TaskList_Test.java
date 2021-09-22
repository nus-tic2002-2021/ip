package duke.task.model;

import duke.task.aggregator.TaskList;
import duke.testHelper.TestStream;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class TaskList_Test extends TestStream {
    @Ignore
    @Test
    public void TestToDo() throws Exception {

        TaskList tl = new TaskList();
        String task0Description = "task 0";
        tl.addTask(new ToDo(task0Description,0,false));
        assertEquals( task0Description,tl.getAllAsArray()[0].getTaskDescription());
        String task1Description = "task 1";
        tl.addTask(new ToDo(task1Description,1,false));
        assertEquals( task1Description,tl.getAllAsArray()[1].getTaskDescription());
    }
    }
