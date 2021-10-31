package tasks;

import exceptions.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void constructor_newEmptyTaskList_success() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getListSize());
    }

    @Test
    public void getListSize_validTaskList_success() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getListSize());

        taskList.addTask(new Task("random task"));
        assertEquals(1, taskList.getListSize());
    }

    @Test
    public void setDone_validTaskId_success() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("random task"));
        taskList.setDone(1);
    }

    @Test
    public void setDone_invalidTaskId_exception() throws DukeException {
        TaskList taskList = new TaskList();
        int id = 1;

        try {
            taskList.setDone(id);
        } catch (DukeException e) {
            assertEquals("Task with id " + id +" is not found.", e.getMessage());
        }
    }

    @Test
    public void deleteTask_validTaskId_success() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("random task"));
        taskList.deleteTask(1);
        assertEquals(0, taskList.getListSize());
    }

    @Test
    public void deleteTask_invalidTaskId_exception() throws DukeException {
        TaskList taskList = new TaskList();
        int id = 1;

        try {
            taskList.deleteTask(id);
        } catch (DukeException e) {
            assertEquals("Task with id " + id +" is not found.", e.getMessage());
        }
    }

    @Test
    public void searchTask_validKeyword_success() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("random task"));
        taskList.addTask(new Task("random task 2"));
        taskList.addTask(new Task("not included"));

        ArrayList<Task> searchResult = taskList.searchTask("random");
        assertEquals(2, searchResult.size());
        assertEquals("random task", searchResult.get(0).getDescription());
        assertEquals("random task 2", searchResult.get(1).getDescription());
    }

    @Test
    public void addTask_multipleValidTasks_success() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getListSize());

        taskList.addTask(new Task("random task 1"));
        taskList.addTask(new Task("random task 2"));
        taskList.addTask(new Task("random task 3"));
        assertEquals(3, taskList.getListSize());
    }

    @Test
    public void getTaskSchedule_validDate_success() {
        TaskList taskList = new TaskList();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        taskList.addTask(new EventTask("event 1", LocalDateTime.parse("2021-10-18 1200", dateTimeFormatter),
                LocalDateTime.parse("2021-10-18 1300", dateTimeFormatter)));
        taskList.addTask(new EventTask("event 1", LocalDateTime.parse("2021-10-19 1200", dateTimeFormatter),
                LocalDateTime.parse("2021-10-19 1300", dateTimeFormatter)));
        assertEquals(2, taskList.getListSize());

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<Task> schedule = taskList.getTaskSchedule(LocalDate.parse("2021-10-18", dateFormatter));
        assertEquals(1, schedule.size());
        assertEquals("event 1", schedule.get(0).getDescription());
    }
}
