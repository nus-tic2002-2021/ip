package duke;


import static duke.dukeutility.parser.TaskToJsonParser.parseDeadlineAsJson;
import static duke.dukeutility.parser.TaskToJsonParser.parseEventAsJson;
import static duke.dukeutility.parser.TaskToJsonParser.parseToDoAsJson;
import static duke.dukeutility.validator.StringValidator.isSubstring;
import static duke.task.TaskComparator.isTaskWithinNextDays;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.JsonArray;
import duke.task.TaskComparator;
import duke.task.aggregator.TaskList;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Task;
import duke.task.model.ToDo;



public class TaskManager {
    // tasks is a "controlled" component. It is a storage for defined properties, i.e tasks ids are set by TaskManager.
    private final TaskList tasks = new TaskList();
    private int serialNo = 0;


    private int rollSerialNo() {
        while (this.tasks.containsKey(this.serialNo)) {
            this.serialNo++;
        }
        return this.getSerialNo();
    }

    private int getSerialNo() {
        return this.serialNo;
    }

    /**
     * Add a todo to this task manager.
     *
     * @param taskDescription
     * @return
     */
    public ToDo addNewToDo(String taskDescription) {
        ToDo task = new ToDo(taskDescription, this.rollSerialNo(), false);
        this.tasks.addTask(task);
        return task;
    }

    /**
     * Add a deadline to this task manager.
     *
     * @param taskDescription
     * @param deadlineString
     * @return
     */
    public Deadline addNewDeadline(String taskDescription, LocalDateTime deadlineString) {
        Deadline deadLine = new Deadline(taskDescription, deadlineString, this.rollSerialNo(), false);
        this.tasks.addTask(deadLine);
        return deadLine;
    }

    /**
     * Add a event to this task manager.
     *
     * @param taskDescription
     * @param from
     * @param to
     * @return
     */
    public Event addNewEvent(String taskDescription, LocalDateTime from, LocalDateTime to) {
        Event event = new Event(taskDescription, from, to, this.rollSerialNo(), false);
        this.tasks.addTask(event);
        return event;
    }


    /**
     * add task to collection
     *
     * @param toDo
     * @return
     */
    public Task importTask(Task task) {
        this.tasks.addTask(task);
        return task;
    }

    public Integer getSize() {
        return this.tasks.getSize();
    }

    public ArrayList<Task> getAllAsArray() {
        return this.tasks.getAllAsArray();
    }

    public ArrayList<Task> getTasksWithKeywordInDescription(String keyword) {
        ArrayList<Task> all = this.tasks.getAllAsArray();
        ArrayList<Task> filtering = new ArrayList<>();

        keyword = keyword.toLowerCase();
        for (Task t : all) {
            if (isSubstring(t.getTaskDescription().toLowerCase(), keyword)) {
                filtering.add(t);
            }
        }
        return filtering;
    }
    public ArrayList<Task> getTasksWithId(Integer id) {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(this.tasks.getTaskById(id));

        return taskList;
    }

    public ArrayList<Task> getTasksForNextDays(Integer period) {
        ArrayList<Task> all = this.tasks.getAllAsArray();
        ArrayList<Task> filtering = new ArrayList<>();
        for (Task t : all) {
            if (isTaskWithinNextDays(t, period)) {
                filtering.add(t);
            }
        }
        filtering.sort(TaskComparator::compareTaskDate);
        return filtering;
    }


    /**
     * Sort tasks into map by description. Return entries with more than one tasks.
     *
     * @return
     */
    public HashMap<String, ArrayList<Task>> getDuplicateDescriptionsAsArray() {

        HashMap<String, ArrayList<Task>> result = new HashMap<>();
        this.tasks.getContainer().forEach((thisId, task) -> {
            String desc = task.getTaskDescription();
            ArrayList<Task> tasks = result.get(desc);
            if (tasks == null) {
                tasks = new ArrayList<>();
            }
            tasks.add(task);
            result.put(desc, tasks);
        });

        result.entrySet().removeIf(entry -> !(entry.getValue().size() > 1));

        return result;
    }


    public Task getTaskById(Integer taskId) {
        return this.tasks.getTaskById(taskId);
    }

    public Task getTaskByIdAndSetDoneStatus(Integer taskId, Boolean done) {
        Task target = this.getTaskById(taskId);
        target.setDoneStatus(done);
        return target;
    }

    public Task getTaskByIdAndSetCompleted(Integer taskId) {
        return this.getTaskByIdAndSetDoneStatus(taskId, true);
    }

    public Task getTaskByIdAndSetIncomplete(Integer taskId) {
        return this.getTaskByIdAndSetDoneStatus(taskId, false);
    }

    public Task deleteTaskById(Integer taskId) {
        return this.tasks.removeTaskById(taskId);
    }

    public JsonArray getAllAsJson() {
        ArrayList<Task> tasks = this.getAllAsArray();
        JsonArray tasksJson = new JsonArray();
        for (Task task : tasks) {
            if (task instanceof Event) {
                tasksJson.add(parseEventAsJson((Event) task));
            } else if (task instanceof Deadline) {
                tasksJson.add(parseDeadlineAsJson((Deadline) task));
            } else if (task instanceof ToDo) {
                tasksJson.add(parseToDoAsJson((ToDo) task));
            }
        }
        return tasksJson;
    }

    public Boolean containsTaskId(Integer taskId) {
        return this.tasks.containsKey(taskId);
    }

}
