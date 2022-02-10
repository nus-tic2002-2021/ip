package duke.command;

import duke.app.TaskList;
import duke.app.Ui;
import duke.exceptions.FileProcessError;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AddCommandTest {
    List<Task> tasksBefore;
    TaskList tasklist;
    Ui ui = new Ui();
    Storage storage;
    AbstractCommand addCommand;
    Map<String, String> parsedUserInput;

    @BeforeEach
    void setUp() {
        tasksBefore = new ArrayList<>();
        tasklist = new TaskList();
        parsedUserInput = new HashMap<>();
        addCommand = new AddCommand();
        try {
            storage = new Storage("./src/test/resource/testAddCommand.txt");
        } catch (FileProcessError e) {
            e.printStackTrace();
        }
    }

    @Test
    void execute_success() {
        tasksBefore.add(new Todo("reading"));

        parsedUserInput.put("TaskType", "TODO");
        parsedUserInput.put("NameOrIndex", "reading");
        tasklist.setParsedUserInputs(parsedUserInput);

        addCommand.execute(tasklist, storage, ui);
        assertEquals(tasksBefore, tasklist.getTaskList());
    }

    @Test
    void execute_redundantTask_fail() {
        tasksBefore.add(new Todo("reading"));
        tasksBefore.add(new Todo("reading"));

        parsedUserInput.put("TaskType", "TODO");
        parsedUserInput.put("NameOrIndex", "reading");
        tasklist.setParsedUserInputs(parsedUserInput);

        addCommand.execute(tasklist, storage, ui);
        assertNotEquals(tasksBefore, tasklist.getTaskList());
    }
}