package duke.dukeutility.validator;

import static duke.dukeutility.definition.TaskField.TASK_FIELD_TYPE;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import duke.dukeutility.enums.JsonTaskType;

public class JsonObjectValidator {

    public static Boolean isNotNullJsonPropertyTaskType(JsonObject jsonObj) {
        return jsonObj.has(TASK_FIELD_TYPE);
    }

    private static Boolean isJsonTypeOf(JsonObject jsonObj, JsonTaskType enumVal) {
        JsonElement elementTaskType = jsonObj.get(TASK_FIELD_TYPE);
        String taskType = elementTaskType.getAsString();
        try {
            return JsonTaskType.valueOf(taskType) == enumVal;
        } catch (Exception e) {
            return false;
        }
    }

    public static Boolean isJsonTypeToDo(JsonObject jsonObj) {
        return isJsonTypeOf(jsonObj, JsonTaskType.ToDo);
    }

    public static Boolean isJsonTypeDeadline(JsonObject jsonObj) {
        return isJsonTypeOf(jsonObj, JsonTaskType.Deadline);
    }

    public static Boolean isJsonTypeEvent(JsonObject jsonObj) {
        return isJsonTypeOf(jsonObj, JsonTaskType.Event);
    }
}
