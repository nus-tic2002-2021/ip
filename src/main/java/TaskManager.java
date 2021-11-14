import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class TaskManager {
    private final static String DETECT_ADD_TODO = "todo";
    private final static String DETECT_ADD_EVENT = "event";
    private final static String DETECT_ADD_DEADLINE = "deadline";
    private final Logger logger = new Logger();
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskManager() {
        logger.init("");
    }

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

    /**
     * Returns a Task object based on the task's information and type.
     * The task information for <a href="#{@link}">{@link Todo}</a> has no restrictions.
     * The task information for <a href="#{@link}">{@link Deadline}</a> must contain "/by", event date and end time.
     * The task information for <a href="#{@link}">{@link Event}</a> must contain "/at", event date, start and end time.
     * The taskType must be either <a href="#{@link}">{@link Todo}</a>, <a href="#{@link}">{@link Event}</a> or <a href="#{@link}">{@link Deadline}</a>
     *
     * @param taskInfo the information of the task
     * @param taskType the type of the task, e.g. event, deadline or todo
     * @return Task
     * @see Task
     * @see Todo
     * @see Deadline
     * @see Event
     */
    public Task createTask(String taskInfo, String taskType) {
        assert !Objects.equals(taskInfo, "");
        assert (Objects.equals(taskType, "event") || Objects.equals(taskType, "todo") || Objects.equals(taskType, "deadline"));
        switch (taskType.trim()) {
            case DETECT_ADD_TODO:
                Todo t = new Todo(taskInfo, getId());
                addTask(t);
                return t;
            case DETECT_ADD_EVENT:
                ArrayList<String> addInfo = new ArrayList(Arrays.asList(taskInfo.split("/at")));
                logger.info("add info for event: " + addInfo);
                Event e = new Event(addInfo.get(0), addInfo.get(1), getId());
                addTask(e);
                return e;
            case DETECT_ADD_DEADLINE:
                ArrayList<String> deadlineInfo = new ArrayList(Arrays.asList(taskInfo.split("/by")));
                Deadline d = new Deadline(deadlineInfo.get(0), deadlineInfo.get(1), getId());
                addTask(d);
                return d;
        }
        return new Task(taskInfo, getId());
    }

    public void listTasks() {
        listTasks(tasks);
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

    public Task markTaskDone(Integer taskId) {
        Task tx = null;
        if (tasks == null) {
            return null;
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (Objects.equals(t.getId(), taskId)) {
                tx = t;
                Todo todo = (Todo) t;
                todo.setDone(true);
                t = (Task) todo;
                tasks.set(i, t);
            }
        }
        return tx;
    }

    public Task deleteTask(Integer taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() != null && tasks.get(i).getId().equals(taskId)) {
                Task t = tasks.get(i);
                tasks.remove(t);
                return t;
            }
        }
        return null;
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
                logger.info("looking at task: " + t + " date for this task " + e.getDate());
                if (e.getDate().equals(date)) {
                    logger.info("added successfully to results " + e);
                    results.add(e);
                }
            } catch (ClassCastException e) {
                logger.info("casting to deadline class got error, item could be todo type, error: " + e.getMessage() + " object: " + t);
            }
        });
        return results;
    }

    public Integer getId() {
        return getNumOfTasks();
    }

    public Integer getNumOfTasks() {
        return tasks.size();
    }
}