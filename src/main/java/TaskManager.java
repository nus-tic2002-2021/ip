import java.util.ArrayList;
import java.util.Arrays;

public class TaskManager {
    private final static String DETECT_ADD_TODO = "todo";
    private final static String DETECT_ADD_EVENT = "event";
    private final static String DETECT_ADD_DEADLINE = "deadline";

    Global global = new Global();

    public Task createTask(String taskInfo, String instruction) {
        switch (instruction) {
            case DETECT_ADD_TODO:
                return new Todo(taskInfo, global.getId());
            case DETECT_ADD_EVENT:
                ArrayList<String> addInfo = new ArrayList(Arrays.asList(taskInfo.split("/at")));
                return new Event(addInfo.get(0), addInfo.get(1), global.getId());
            case DETECT_ADD_DEADLINE:
                ArrayList<String> deadlineInfo = new ArrayList(Arrays.asList(taskInfo.split("/by")));
                return new Deadline(deadlineInfo.get(0), deadlineInfo.get(1), global.getId());
        }
        return new Task(taskInfo, global.getId());
    }
}
