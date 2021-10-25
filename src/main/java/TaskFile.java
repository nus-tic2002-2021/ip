import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class TaskFile extends FileAccessor {
    public TaskFile(String path) {
        super(path);
    }

    public void add(Task t) throws Exception {

    }

    public void delete(Task t) throws Exception {

    }

    public ArrayList<Task> load() throws Exception {
        init();
        ArrayList<Task> list = new ArrayList<Task>();
        int id = 0;
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            list.add(parseLineToTask(s.nextLine(), id));
            id++;
        }
        return list;
    }

    public Task parseLineToTask(String l, int id) throws Exception {
        if (Objects.equals(l, "")) {
            throw new Exception("no content");
        }

        String[] parts = l.split(",");
        String taskType = parts[0];
        String taskDesc = parts[1];
        boolean taskStatus = Boolean.parseBoolean(parts[2]);
        Task task = new Task();
        task.createTask(taskDesc, taskType);
        task.addStatus(taskStatus);
        return task;
    }
}
