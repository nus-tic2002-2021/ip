package duke;

import duke.task.*;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the TaskList
 */

public class Storage {
    Path filePath;
    Path folderPath;

    public Storage() {
        String home = System.getProperty("user.home");
        filePath = Paths.get(home, "dukeData", "duke.txt");
        folderPath = Paths.get(home, "dukeData");

    }
    public void fileLocation() throws IOException {
        if (Files.notExists(folderPath)) {
            Files.createDirectory(folderPath);
        }
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }
    /**
     * Read the task list
     * @return task list
     */

    public TaskList read() {
        TaskList taskList = null;
        try {
            fileLocation();
            List<String> tasks = Files.readAllLines(filePath, Charset.defaultCharset());
            taskList = this.TaskListDecoder(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Stores a task list in a filePath
     * @param taskList to store task list
     */

    public void save(TaskList taskList) {
        try {
            List<String> encodedTasks = this.TaskListEncoder(taskList);
            Files.write(filePath, encodedTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an encoded String task list to store.
     * @return a String task list
     */

    public List<String> TaskListEncoder (TaskList taskList) {
        List<String> TaskEncoder = new ArrayList<>();
        for (Task t : taskList.getList()) {
            TaskEncoder.add(t.taskEncode());
        }
        return TaskEncoder;
    }

    /**
     * Returns a decoded task list to use
     * If the text is an empty String, an empty ArrayList will be returned as expected.
     * @param TaskEncoder the encoded task list
     * @return encoded task list
     */


    public TaskList TaskListDecoder(List<String> TaskEncoder) {
        List<Task> TaskDecoder = new ArrayList<>();
        for (String s : TaskEncoder) {
            switch(s.charAt(0)) {
                case ('T'):
                    TaskDecoder.add(Todo.decode(s));
                    break;
                case ('D'):
                    TaskDecoder.add(Deadlines.decode(s));
                    break;
                case ('E'):
                    TaskDecoder.add(Event.decode(s));
                    break;
                default:
                    break;
            }
        }
        return new TaskList(TaskDecoder);
    }

}


