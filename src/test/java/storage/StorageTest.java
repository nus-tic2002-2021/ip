package storage;

import org.junit.jupiter.api.Test;
import tasklist.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void constructor() {
        Storage s = new Storage("src/data/test.txt");
        assertEquals("src/data/test.txt", s.getFilePath());
    }

    @Test
    public void readFromFile() {
        Storage s = new Storage("src/data/test.txt");
        try {
            ArrayList<Task> tasks = s.readFromFile();
        } catch (FileNotFoundException e) {
            assertEquals(new FileNotFoundException().getMessage(), e.getMessage());
        }
    }

    @Test
    public void writeToFile() {
        Storage s = new Storage("src/data/test.txt");
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            s.writeToFile(tasks);
        } catch (IOException e) {
            assertEquals(new IOException().getMessage(), e.getMessage());
        }
    }
}