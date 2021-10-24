import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    Path filePath;
    Path folderPath;

    private Storage() {
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
    public TaskList read() {
        
    }

    public List<String> TaskListEncoder (TaskList taskList) {
        List<String> TaskEncoder = new ArrayList<>();
        for (Task t : taskList.getList()) {
            TaskEncoder.add(t.taskEncode());
        }
        return TaskEncoder;
    }
    public TaskList TaskListDecoder(List<String> TaskEncoder) {
        List<Task> TaskDecoder = new ArrayList<>();
        for (String s : TaskEncoder) {
            switch(s.charAt(0)) {
                case ('T'):
                    TaskDecoder.add(Todo.taskDecode(s));
                    break;
                case ('D'):
                    TaskDecoder.add(Deadlines.taskDecode(s));
                    break;
                case ('E'):
                    TaskDecoder.add(Event.taskDecode(s));
                    break;
                default:
                    break;
            }
        }
        return new TaskList(TaskDecoder);
    }

}

}
