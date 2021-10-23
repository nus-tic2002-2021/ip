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

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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


    public Integer getSize() {
        return this.tasks.getSize();
    }

    public ArrayList<Task> getAllAsArray() {
        return this.tasks.getAllAsArray();
    }

    public ArrayList<Task> getTasksWithWord(String keyword) {
        ArrayList<Task> all = this.tasks.getAllAsArray();
        ArrayList<Task> filtering = new ArrayList<>();
        for (Task t : all) {
            if (t.descContainsKeyword(keyword)) {
                filtering.add(t);
            }
        }
        return filtering;
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
     * add todo to collection
     *
     * @param toDo
     * @return
     */
    public ToDo importToDo(ToDo toDo) {
        this.tasks.addTask(toDo);
        return toDo;
    }


    /**
     * add deadline to collection
     *
     * @param deadline
     * @return
     */

    public Deadline importDeadline(Deadline deadline) {
        this.tasks.addTask(deadline);
        return deadline;

    }


    /**
     * add event to collection
     *
     * @param event
     * @return
     */

    public Event importEvent(Event event) {
        this.tasks.addTask(event);
        return event;
    }

    /**
     * Converts a JSON formatted task to java object.
     *
     * @param jsonObj task
     * @return task object
     * @throws Exception if not recognised as a task
     */
    public final Task objectify(JsonObject jsonObj) throws Exception {
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
