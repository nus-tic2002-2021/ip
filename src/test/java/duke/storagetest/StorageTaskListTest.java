package duke.storagetest;

import duke.storage.StorageTaskList;
import duke.task.Deadline;
import duke.task.Events;
import duke.task.TaskList;
import duke.task.ToDos;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTaskListTest {
    @Test
    public void storage() throws IOException {
        LocalDateTime sample = LocalDateTime.of(2021,12,25,16,0,0);
        TaskList tasks = new TaskList();
        ToDos toDos = new ToDos("eat");
        Deadline deadline = new Deadline("return book", sample);
        Events event = new Events("Finish Homework",sample.toLocalDate(),sample.toLocalTime(),sample.toLocalTime().plusHours(2));
        tasks.addTask(toDos);
        assert tasks.getListSize()==1:"There should exist  1 task";
        tasks.addTask(deadline);
        assert tasks.getListSize()==2:"There should exist  2 task";
        tasks.addTask(event);
        assert tasks.getListSize()==3:"There should exist  3 task";
        StorageTaskList store = new StorageTaskList("Testing.txt");
        assertFalse(store.existence());
        store.createFile();
        store.save(tasks);
        assertTrue(store.existence());

        StorageTaskList store2 = new StorageTaskList("Testing.txt");
        assertEquals(tasks.getLastTask().toString(), store2.load().getLastTask().toString());

        File cleanUP = new File(System.getProperty("user.dir") + "/src/main/java/Duke/storage/Testing.txt"); // create a File for the given file path
        boolean wrapUp = cleanUP.delete();
    }
}
