package duke.dukeutility.parser;

import static duke.dukeutility.definition.TaskField.TASK_FIELD_DEADLINE;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_DESCRIPTION;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_DONE_STATUS;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_FROM;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_TASK_ID;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_TO;
import static duke.dukeutility.parser.DateParser.parseStringAsLocalDateTime;
import static duke.dukeutility.validator.JsonObjectValidator.isJsonTypeDeadline;
import static duke.dukeutility.validator.JsonObjectValidator.isJsonTypeEvent;
import static duke.dukeutility.validator.JsonObjectValidator.isJsonTypeToDo;
import static duke.dukeutility.validator.JsonObjectValidator.isNotNullJsonPropertyTaskType;

import java.time.LocalDateTime;

import com.google.gson.JsonObject;

import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Task;
import duke.task.model.ToDo;

public class JsonTaskToObjectParser extends Parser {
    private JsonTaskToObjectParser() {
    }

    /**
     * Converts a JSON formatted task to POJO.
     *
     * @param jsonObj task
     * @return task object
     * @throws Exception if not recognised as a task
     */
    public static Task jsonTaskToPojo(JsonObject jsonObj) throws Exception {
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


    public static Boolean getJsonPropertyDoneStatus(JsonObject jsonObj) {
        return jsonObj.get(TASK_FIELD_DONE_STATUS).getAsBoolean();
    }

    public static String getJsonPropertyTaskDescription(JsonObject jsonObj) {
        return jsonObj.get(TASK_FIELD_DESCRIPTION).getAsString();
    }

    public static LocalDateTime getJsonPropertyDeadline(JsonObject jsonObj) throws Exception {
        return parseStringAsLocalDateTime(jsonObj.get(TASK_FIELD_DEADLINE).getAsString());
    }

    public static LocalDateTime getJsonPropertyFrom(JsonObject jsonObj) throws Exception {
        return parseStringAsLocalDateTime(jsonObj.get(TASK_FIELD_FROM).getAsString());
    }

    public static LocalDateTime getJsonPropertyTo(JsonObject jsonObj) throws Exception {
        return parseStringAsLocalDateTime(jsonObj.get(TASK_FIELD_TO).getAsString());
    }

    public static Integer getJsonPropertyTaskId(JsonObject jsonObj) {
        return jsonObj.get(TASK_FIELD_TASK_ID).getAsInt();
    }
}
