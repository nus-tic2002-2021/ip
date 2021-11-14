import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

public class StorageDTO extends StorageDAO {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public StorageDTO(String path) {
        super(path);
    }

    public ArrayList<Task> loadInto(TaskManager tm) {
        try {
            init();
            ArrayList<Task> list = new ArrayList<Task>();
            int id = 0;
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                Task t = parseLineToTask(s.nextLine(), id, tm);
                list.add(t);
                logger.finest("added task successfully to list" + t);
                id++;
            }
            tm.setTasks(list);
            return list;
        } catch (Exception e) {
            logger.finest("got error loading file, error: " + e);
        }
        return new ArrayList<Task>();
    }

    public String parseTaskToLine(Task e) throws Exception {
        if (e == null) {
            throw new Exception("task to parse is empty");
        }
        return e.toTask();
    }

    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(path);
            tasks.forEach(t -> {
                try {
                    String task = parseTaskToLine(t);
                    fw.write(task + "\n");
                    logger.finest("task written properly" + task);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            fw.close();
            logger.finest("file has been saved properly");
        } catch (Exception e) {
            System.out.println("saving failed, error: " + e);
        }
    }

    //deadline,party to be at 26/20/2021,0
    public Task parseLineToTask(String l, int id, TaskManager tm) throws Exception {
        if (Objects.equals(l, "")) {
            throw new Exception("no content");
        }

        String[] parts = l.split(",");
        String taskType = parts[0];
        String taskDesc = parts[1];
        boolean taskStatus = Boolean.parseBoolean(parts[2]);
        Task t = tm.createTask(taskDesc, taskType);
        if (!Objects.equals(t.getType(), "task")) {
            Todo todo = (Todo) t;
            todo.setDone(taskStatus);
        }
        return t;
    }
}
