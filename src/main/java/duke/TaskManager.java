package duke;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import duke.task.aggregator.TaskList;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Task;
import duke.task.model.ToDo;

import java.util.ArrayList;

import static duke.dukeUtility.parser.JsonTaskToObjectParser.*;
import static duke.dukeUtility.parser.TaskToJsonParser.*;
import static duke.dukeUtility.validator.JsonObjectValidator.*;

public class TaskManager {
    private TaskList _activeTasks = new TaskList();
    private int _serialNo = 0;

    private int rollSerialNo() {
        while (this._activeTasks.containsKey(this._serialNo)) {
            this._serialNo++;
        }
        return this.getSerialNo();
    }
    private int getSerialNo() {
        return this._serialNo;
    }
    public ToDo addNewToDo(String taskDescription) {
        ToDo task = new ToDo(taskDescription,this.rollSerialNo(),false);
        this._activeTasks.addTask(task);
        return task;
    }
    public Event addNewEvent(String taskDescription, String from, String to) {
        Event event = new Event(taskDescription, from, to, this.rollSerialNo(), false);
        this._activeTasks.addTask(event);
        return event;
    }
    public Deadline addNewDeadline(String taskDescription, String deadlineString) {
        Deadline deadLine = new Deadline(taskDescription, deadlineString, this.rollSerialNo(), false);
        this._activeTasks.addTask(deadLine);
        return deadLine;
    }
    public Integer getSize(){
        return this._activeTasks.getSize();
    }
    public ArrayList<Task> getAllAsArray() {
        return this._activeTasks.getAllAsArray();
    }
    public Boolean containsTaskId(Integer taskId) {
        return this._activeTasks.containsKey(taskId);
    }
    public Task getTaskById(Integer taskId) {
        return this._activeTasks.getTaskById(taskId);
    }
    public Task getTaskByIdAndSetDoneStatus(Integer taskId, Boolean done) {
        Task target = this.getTaskById(taskId);
        target.setDoneStatus(done);
        return target;
    }
    public Task softDeleteById(Integer taskId) {
        return this._activeTasks.removeTaskById(taskId);
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

    public ToDo importToDo(ToDo toDo) {
        this._activeTasks.addTask(toDo);
        return toDo;
    }

    public Event importEvent(Event event) {
        this._activeTasks.addTask(event);
        return event;
    }

    public Deadline importDeadline(Deadline deadline) {
        this._activeTasks.addTask(deadline);
        return deadline;

    }

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
            String deadline = getJsonPropertyDeadline(jsonObj);
            return new Deadline(taskDescription, deadline, taskId, done);
        } else if (isJsonTypeEvent(jsonObj)) {
            Integer taskId = getJsonPropertyTaskId(jsonObj);
            String taskDescription = getJsonPropertyTaskDescription(jsonObj);
            Boolean done = getJsonPropertyDoneStatus(jsonObj);
            String from = getJsonPropertyFrom(jsonObj);
            String to = getJsonPropertyTo(jsonObj);
            return new Event(taskDescription, from, to, taskId, done);
        }
        throw new Exception("Json object not recognised as a task Object");
    }
    
    
}
