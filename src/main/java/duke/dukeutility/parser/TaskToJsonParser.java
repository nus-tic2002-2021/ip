package duke.dukeutility.parser;

import static duke.dukeutility.definition.TaskField.TASK_FIELD_DEADLINE;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_DESCRIPTION;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_DONE_STATUS;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_FROM;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_TASK_ID;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_TO;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_TYPE;
import static duke.dukeutility.parser.DateParser.parseLocalDateTimeAsString;

import com.google.gson.JsonObject;

import duke.dukeutility.enums.JsonTaskType;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.ToDo;


public class TaskToJsonParser extends Parser {

    private TaskToJsonParser() {
    }

    /**
     * Format event as JSON
     * @param event
     * @return event as JSON
     */
    public static JsonObject parseEventAsJson(Event event) {
        JsonObject obj = new JsonObject();
        obj.addProperty(TASK_FIELD_TYPE, JsonTaskType.Event.toString());
        obj.addProperty(TASK_FIELD_TASK_ID, event.getTaskId());
        obj.addProperty(TASK_FIELD_DESCRIPTION, event.getTaskDescription());
        obj.addProperty(TASK_FIELD_DONE_STATUS, event.isDone());
        obj.addProperty(TASK_FIELD_FROM, parseLocalDateTimeAsString(event.getFrom()));
        obj.addProperty(TASK_FIELD_TO, parseLocalDateTimeAsString(event.getTo()));
        return obj;
    }


    /**
     * Format deadline as JSON
     * @param deadline
     * @return deadline as JSON
     */
    public static JsonObject parseDeadlineAsJson(Deadline deadline) {
        JsonObject obj = new JsonObject();
        obj.addProperty(TASK_FIELD_TYPE, JsonTaskType.Deadline.toString());
        obj.addProperty(TASK_FIELD_TASK_ID, deadline.getTaskId());
        obj.addProperty(TASK_FIELD_DESCRIPTION, deadline.getTaskDescription());
        obj.addProperty(TASK_FIELD_DONE_STATUS, deadline.isDone());
        obj.addProperty(TASK_FIELD_DEADLINE, parseLocalDateTimeAsString(deadline.getDeadline()));
        return obj;
    }
    /**
     * Format toDo as JSON
     * @param toDo
     * @return toDo as JSON
     */
    public static JsonObject parseToDoAsJson(ToDo toDo) {
        JsonObject obj = new JsonObject();
        obj.addProperty(TASK_FIELD_TYPE, JsonTaskType.ToDo.toString());
        obj.addProperty(TASK_FIELD_TASK_ID, toDo.getTaskId());
        obj.addProperty(TASK_FIELD_DESCRIPTION, toDo.getTaskDescription());
        obj.addProperty(TASK_FIELD_DONE_STATUS, toDo.isDone());
        return obj;
    }
}
