import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class TaskManager {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final static String DETECT_ADD_TODO = "todo";
    private final static String DETECT_ADD_EVENT = "event";
    private final static String DETECT_ADD_DEADLINE = "deadline";

    private final static String STMT_DONE = "Nice! I've marked this task as done: ";
    private final static String STMT_DELETE = "Noted. I've removed this task: ";

    private ArrayList<Task> tasks = new ArrayList<>();
    Global global = new Global();

    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        tasks.stream().filter(t -> t.getDescription().contains(keyword)).forEach(results::add);
        return results;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task createTask(String taskInfo, String instruction) {
        switch (instruction) {
            case DETECT_ADD_TODO:
                Todo t = new Todo(taskInfo, global.getId());
                addTask(t);
                return t;
            case DETECT_ADD_EVENT:
                ArrayList<String> addInfo = new ArrayList(Arrays.asList(taskInfo.split("/at")));
                Event e = new Event(addInfo.get(0), addInfo.get(1), global.getId());
                addTask(e);
                return e;
            case DETECT_ADD_DEADLINE:
                ArrayList<String> deadlineInfo = new ArrayList(Arrays.asList(taskInfo.split("/by")));
                Deadline d = new Deadline(deadlineInfo.get(0), deadlineInfo.get(1), global.getId());
                addTask(d);
                return d;
        }
        return new Task(taskInfo, global.getId());
    }

    public void listTasks() {
        if (tasks.toArray().length == 0) {
            return;
        }
        tasks.forEach(l -> System.out.println(l.toString()));
    }

    public void listTasks(ArrayList<Task> l) {
        if (tasks.toArray().length == 0) {
            return;
        }
        l.forEach(x -> System.out.println(x.toString()));
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void markTaskDone(Integer taskId) {
        tasks.stream().filter(t -> t.getId().equals(taskId)).forEach(t -> {
            System.out.println(STMT_DONE);
            Todo task = (Todo) t;
            task.setDone(true);
        });
    }

    public void deleteTask(ArrayList<Task> list, Integer taskId) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() != null && list.get(i).getId().equals(taskId)) {
                System.out.println(STMT_DELETE);
                System.out.println(list.get(i).toString());
                list.remove(list.get(i));
                return;
            }
        }
    }

    public ArrayList<Task> viewTaskOn(LocalDate date) {
        logger.info("viewTasksOn date: " + date + ", current task list: " + tasks);
        ArrayList<Task> results = new ArrayList<>();
        if (date == null) {
            logger.info("viewTasksOn got null date");
            return results;
        }
        tasks.forEach(t -> {
            try {
                Deadline e = (Deadline) t;
                logger.finest("looking at task: " + t + " date for this task " + e.getDate());
                if (e.getDate().equals(date)) {
                    logger.finest("added successfully to results " + e);
                    results.add(e);
                }
            } catch (ClassCastException e) {
                logger.info("casting to deadline class got error, item could be todo type, error: " + e.getMessage() + " object: " + t);
            }
        });
        return results;
    }
}