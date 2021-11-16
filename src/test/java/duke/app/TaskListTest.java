package duke.app;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    Map<String, String> parsedUserInputs;
    TaskList taskList;
    List<Task> beforeTasks;

    @BeforeEach
    void setUp() {
        parsedUserInputs = new HashMap<>();

        beforeTasks = new ArrayList<>();
        beforeTasks.add(new Deadline("return book", "2021-11-25 18:00"));
        beforeTasks.add(new Todo("reading"));
        beforeTasks.add(new Event("project meeting", "2021-11-22 15:00"));

        taskList = new TaskList(beforeTasks);
    }

    @Test
    void deleteTask_success() {
        //note that the index is checked when parsing the input
        parsedUserInputs.put("NameOrIndex", "2");
        taskList.setParsedUserInputs(parsedUserInputs);
        taskList.deleteTask();
        beforeTasks.remove(1);
        assertEquals(beforeTasks, taskList.getTaskList());
    }

    @Test
    void changeTaskStatus_success() {
        parsedUserInputs.put("NameOrIndex", "2");
        taskList.setParsedUserInputs(parsedUserInputs);
        taskList.changeTaskStatus();

        beforeTasks.get(1).setDone(true);
        assertEquals(beforeTasks.get(1).isDone(), taskList.getTaskList().get(1).isDone());
    }

    @Test
    void getTasksReminder_success() {
        parsedUserInputs.put("NameOrIndex", "7");
        taskList.setParsedUserInputs(parsedUserInputs);
        List<Task> criticalTasks = taskList.getTasksReminder();

        beforeTasks.remove(0);
        beforeTasks.remove(0);

        assertEquals(beforeTasks, criticalTasks);
    }

    @Test
    void addTask_success() {
        boolean expectedSuccess = true;

        parsedUserInputs.put("TaskType", "TODO");
        parsedUserInputs.put("NameOrIndex", "shopping");
        taskList.setParsedUserInputs(parsedUserInputs);
        boolean actualSuccess = taskList.addTask();

        assertEquals(expectedSuccess, actualSuccess);
    }

    @Test
    void addTask_notUnique_fail() {
        boolean expectedSuccess = false;

        parsedUserInputs.put("TaskType", "TODO");
        parsedUserInputs.put("NameOrIndex", "reading");
        taskList.setParsedUserInputs(parsedUserInputs);
        boolean actualSuccess = taskList.addTask();

        assertEquals(expectedSuccess, actualSuccess);
    }

    @Test
    void setTagForOneTask_success() {
        parsedUserInputs.put("NameOrIndex", "2");
        parsedUserInputs.put("Tag", "fun");
        taskList.setParsedUserInputs(parsedUserInputs);
        taskList.setTagForOneTask();

        beforeTasks.get(1).setTag("fun");
        assertEquals("#fun", taskList.getTaskList().get(1).getTag());
    }
}