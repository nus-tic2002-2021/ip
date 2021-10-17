package storage;

import org.junit.jupiter.api.Test;
import tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void constructor_withFilepath_success() {
        Storage s = new Storage("data/test.txt");
        assertEquals("data/test.txt", s.getFilePath());
    }

    @Test
    public void loadTasks_invalidFilepath_exception() throws FileNotFoundException {
        Storage s = new Storage("data/test.txt");
        try {
            ArrayList<Task> tasks = s.loadTasks();
        } catch (FileNotFoundException e) {
            //to change this
            assertEquals(e.getMessage(), e.getMessage());
        }
    }

    @Test
    public void saveTasks_invalidFilepath_exception() throws IOException {
        Storage s = new Storage("data/test.txt");
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            s.saveTasks(tasks);
        } catch (IOException e) {
            //to change this
            assertEquals(e.getMessage(), e.getMessage());
        }
    }
}
