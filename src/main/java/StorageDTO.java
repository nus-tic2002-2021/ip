import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class StorageDTO extends StorageDAO {
    private final Logger logger = new Logger();

    public StorageDTO(String path) {
        super(path);
        logger.init("");
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
                logger.info("added task successfully to list" + t);
                id++;
            }
            tm.setTasks(list);
            return list;
        } catch (Exception e) {
            logger.info("got error loading file, error: " + e);
        }
        return new ArrayList<Task>();
    }

    public String parseTaskToLine(Task e) throws Exception {
        if (e == null) {
            throw new Exception("task to parse is empty");
        }
        try {
            Event event = (Event) e;
            return event.toTask();
        } catch (ClassCastException exception) {
            logger.info("casting task from file to event class got error");
        }

        try {
            Deadline deadline = (Deadline) e;
            return deadline.toTask();
        } catch (ClassCastException exception) {
            logger.info("casting task from file to deadline class got error");
        }

        try {
            Todo todo = (Todo) e;
            return todo.toTask();
        } catch (ClassCastException exception) {
            logger.info("casting task from file to todo class got error");
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
                    logger.info("task written properly" + task);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            fw.close();
            logger.info("file has been saved properly");
        } catch (Exception e) {
            System.out.println("saving failed, error: " + e);
        }
    }

    public Task parseLineToTask(String l, int id, TaskManager tm) throws Exception {
        if (Objects.equals(l, "")) {
            throw new Exception("no content");
        }

        String[] parts = l.split(",");
        String taskType = parts[0];
        String taskDesc = parts[1];
        int taskStatus = Integer.parseInt(parts[2]);
        Task t = tm.createTask(taskDesc, taskType);
        if (!Objects.equals(t.getType(), "task")) {
            Todo todo = (Todo) t;
            todo.setDone(taskStatus != 0);
        }
        return t;
    }
}
