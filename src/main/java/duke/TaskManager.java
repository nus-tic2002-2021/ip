package duke;

import static duke.dukeutility.parser.JsonTaskToObjectParser.getJsonPropertyDeadline;
import static duke.dukeutility.parser.JsonTaskToObjectParser.getJsonPropertyDoneStatus;
import static duke.dukeutility.parser.JsonTaskToObjectParser.getJsonPropertyFrom;
import static duke.dukeutility.parser.JsonTaskToObjectParser.getJsonPropertyTaskDescription;
import static duke.dukeutility.parser.JsonTaskToObjectParser.getJsonPropertyTaskId;
import static duke.dukeutility.parser.JsonTaskToObjectParser.getJsonPropertyTo;
import static duke.dukeutility.parser.TaskToJsonParser.parseDeadlineAsJson;
import static duke.dukeutility.parser.TaskToJsonParser.parseEventAsJson;
import static duke.dukeutility.parser.TaskToJsonParser.parseToDoAsJson;
import static duke.dukeutility.validator.JsonObjectValidator.isJsonTypeDeadline;
import static duke.dukeutility.validator.JsonObjectValidator.isJsonTypeEvent;
import static duke.dukeutility.validator.JsonObjectValidator.isJsonTypeToDo;
import static duke.dukeutility.validator.JsonObjectValidator.isNotNullJsonPropertyTaskType;
import static duke.dukeutility.validator.StringValidator.isSubstring;
import static duke.task.TaskComparator.isTaskWithinNextDays;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import duke.task.TaskComparator;
import duke.task.aggregator.TaskList;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Task;
import duke.task.model.ToDo;

public class TaskManager {
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
    public Boolean containsTaskId(Integer taskId) {
        return this.tasks.containsKey(taskId);
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

    public Task deleteTaskByTaskId(Integer taskId) {
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


    /**
     * Converts a JSON formatted task to POJO.
     *
     * @param jsonObj task
     * @return task object
     * @throws Exception if not recognised as a task
     */
    public final Task jsonTaskToPojo(JsonObject jsonObj) throws Exception {
        if (!isNotNullJsonPropertyTaskType(jsonObj)) {
            throw new Exception("No task type");
        } else if (isJsonTypeToDo(jsonObj)) {
            Integer taskId = getJsonPropertyTaskId(jsonObj);
            String taskDescription = getJsonPropertyTaskDescription(jsonObj);
            Boolean done = getJsonPropertyDoneStatus(jsonObj);
            return new ToDo(taskDescription, taskId, done);
        } else if (isJsonTypeDeadline(jsonObj)) {
            Integer taskId = getJsonPropertyTaskId(jsonObj);
            String taskDescription = getJsonPropertyTaskDescription(jsonObj);
            Boolean done = getJsonPropertyDoneStatus(jsonObj);
            LocalDateTime deadline = getJsonPropertyDeadline(jsonObj);
            return new Deadline(taskDescription, deadline, taskId, done);
        } else if (isJsonTypeEvent(jsonObj)) {
            Integer taskId = getJsonPropertyTaskId(jsonObj);
            String taskDescription = getJsonPropertyTaskDescription(jsonObj);
            Boolean done = getJsonPropertyDoneStatus(jsonObj);
            LocalDateTime from = getJsonPropertyFrom(jsonObj);
            LocalDateTime to = getJsonPropertyTo(jsonObj);
            return new Event(taskDescription, from, to, taskId, done);
        }
        throw new Exception("Json object not recognised as a task Object");
    }


}
