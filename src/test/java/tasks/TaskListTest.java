package tasks;

import exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void constructor_newEmptyTaskList_success(){
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getListSize());
    }

    @Test
    public void constructor_existingTaskList_success(){}

    @Test
    public void getListSize_validTaskList_success(){
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getListSize());

        taskList.addTask(new Task("random task"));
        assertEquals(1, taskList.getListSize());
    }

    @Test
    public void setDone_validTaskId_success() throws DukeException{
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("random task"));
        taskList.setDone(1);
    }

    @Test
    public void setDone_invalidTaskId_exception() throws DukeException{
        TaskList taskList = new TaskList();
        int id = 1;

        try {
            taskList.setDone(id);
        } catch (DukeException e) {
            assertEquals("Task with id " + id +" is not found.", e.getMessage());
        }
    }

    @Test
    public void deleteTask_validTaskId_success() throws DukeException{
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("random task"));
        taskList.deleteTask(1);
        assertEquals(0, taskList.getListSize());
    }

    @Test
    public void deleteTask_invalidTaskId_exception() throws DukeException{
        TaskList taskList = new TaskList();
        int id = 1;

        try {
            taskList.deleteTask(id);
        } catch (DukeException e) {
            assertEquals("Task with id " + id +" is not found.", e.getMessage());
        }
    }

    @Test
    public void addTask_multipleTasks_success(){
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getListSize());

        taskList.addTask(new Task("random task 1"));
        taskList.addTask(new Task("random task 2"));
        taskList.addTask(new Task("random task 3"));
        assertEquals(3, taskList.getListSize());
    }

}
