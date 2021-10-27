package duke.unittest.taskmanagertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.TaskManager;
import duke.testhelper.TestStream;

public class ToggleDoneTest extends TestStream {


    /**
     * Add todos to a task manager.
     */
    @Test
    public void addToDosSetUnsetDone() {
        TaskManager tm = new TaskManager();

        String desc0 = "desc0";
        tm.addNewToDo(desc0);
        assertEquals(desc0, tm.getTaskById(0).getTaskDescription());

        tm.getTaskByIdAndSetCompleted( 0);
        assertTrue(tm.getTaskById(0).isDone());
        tm.getTaskByIdAndSetIncomplete(0);
        assertFalse(tm.getTaskById(0).isDone());
    }
}
