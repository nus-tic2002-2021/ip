import java.util.ArrayList;
import java.util.Arrays;

public class Task {
    private final static String DETECT_ADD_TODO = "todo";
    private final static String DETECT_ADD_EVENT = "event";
    private final static String DETECT_ADD_DEADLINE = "deadline";

    String description;
    Integer id;
    Task task;

    public Task() {

    }

    public Task(String description, Integer id) {
        System.out.println("set task: " + description + id);
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        description = desc;
    }

    public String toString() {
        return description;
    }

    public void createTask(String taskInfo, String instruction) {
        switch (instruction) {
            case DETECT_ADD_TODO:
                task = new Todo(taskInfo, id);
                break;
            case DETECT_ADD_EVENT:
                ArrayList<String> addInfo = new ArrayList(Arrays.asList(taskInfo.split("/at")));
                task = new Event(addInfo.get(0), addInfo.get(1), id);
                break;
            case DETECT_ADD_DEADLINE:
                ArrayList<String> deadlineInfo = new ArrayList(Arrays.asList(taskInfo.split("/by")));
                task = new Deadline(deadlineInfo.get(0), deadlineInfo.get(1), id);
                break;
        }
    }

    public void addStatus(boolean status) throws Exception {
        Todo t = (Todo) task;
        t.setDone(status);
        task = (Task) t;
    }
}

