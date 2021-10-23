package Duke.StorageTests;

import Duke.storage.Storage;
import Duke.task.Deadline;
import Duke.task.Events;
import Duke.task.TaskList;
import Duke.task.ToDos;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @Test
    public void storage() throws IOException {
        LocalDateTime sample = LocalDateTime.of(2021,12,25,16,0,0);
        ToDos toDos = new ToDos("eat", "eat");
        Deadline deadline = new Deadline("return book", sample);
        Events event = new Events("Finish Homework",sample.toLocalDate(),sample.toLocalTime(),sample.toLocalTime().plusHours(2));
        TaskList tasks = new TaskList();
        tasks.addTask(toDos);
        tasks.addTask(deadline);
        tasks.addTask(event);

        Storage store = new Storage("Testing.txt");
        assertFalse(store.existence());
        store.createFile();
        store.save(tasks);
        assertTrue(store.existence());

        Storage store2 = new Storage("Testing.txt");
        assertEquals(tasks.getLastTask().toString(), store2.load().getLastTask().toString());

        File cleanUP = new File(System.getProperty("user.dir") + "/src/main/java/Duke/storage/Testing.txt"); // create a File for the given file path
        cleanUP.delete();
    }
}
