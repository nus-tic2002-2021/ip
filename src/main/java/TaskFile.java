import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class TaskFile extends FileAccessor {
    public TaskFile(String path) {
        super(path);
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

    public String parseTaskToLine(Task e) throws Exception {
        if (e == null) {
            throw new Exception("task to parse is empty");
        }
        Todo t = (Todo) e;
        return t.toTask();
    }

    public void save(ArrayList<Task> tasks) throws Exception {
        FileWriter fw = new FileWriter(path);
        tasks.forEach(t -> {
            try {
                String task = parseTaskToLine(t);
                fw.write(task);
                System.out.println("task written properly" + task);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        fw.close();
        System.out.println("file has been saved properly");
    }

    //deadline,party to be at 26/20/2021,0
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
