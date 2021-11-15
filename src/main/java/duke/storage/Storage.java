package duke.storage;

import duke.exceptions.FileProcessError;
import duke.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    public static final String DEFAULT_FILE_PATH = "./data/duke.txt";
    public String filePath;

    public Storage(String filePath) throws FileProcessError {
        this.filePath = filePath;
        try {
            createFileIfNotExists();
        } catch (IOException e) {
            throw new FileProcessError("Can't create a new file based on file path defined");
        }
    }

    public Storage() throws FileProcessError {
        this(DEFAULT_FILE_PATH);
    }

    private void createFileIfNotExists() throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    public void saveToFile(List<Task> tasks) throws FileProcessError {
        List<String> toSave = FileEncoder.encodeTasks(tasks);
        try (FileWriter writer = new FileWriter(filePath)){
            for(String item: toSave) {
                writer.write(item + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new FileProcessError("Error occurred when saving to file");
        }
    }

    public List<Task> loadFile() throws FileProcessError {
        List<String> originalTasks;
        try (Scanner scanner = new Scanner(new File(filePath))) {
            originalTasks = new ArrayList<>();
            while (scanner.hasNext()) {
                originalTasks.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new FileProcessError("Error occurred when loading the file");
        }
        if (originalTasks.isEmpty()) {
            return null;
        } else {
            return FileDecoder.decodeTasks(originalTasks);
        }
    }
}
