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
        // load the file
        // read the first line
        // convert into a task
        // add into the list
        init();
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public Task parseLineToTask(String l, int id) throws Exception {
        if (Objects.equals(l, "")) {
            throw new Exception("no content");
        }

        String[] parts = l.split(",");
        String taskType = parts[0];
        String taskStatus = parts[1];
        String taskDesc = parts[2];
        Task t = null;
        switch (taskType) {
            case "T":
                Task
        }

    }

}
