package duke.dukeUtility.parser;

import com.google.gson.JsonObject;

import java.time.LocalDateTime;

import static duke.dukeUtility.definition.TaskField.*;

public class JsonTaskToObjectParser extends Parser {
    public static Boolean getJsonPropertyDoneStatus(JsonObject jsonObj) {
        return jsonObj.get(TASK_FIELD_DONE_STATUS).getAsBoolean();
    }

    public static String getJsonPropertyTaskDescription(JsonObject jsonObj) {
        return jsonObj.get(TASK_FIELD_DESCRIPTION).getAsString();
    }

    public static String getJsonPropertyDeadline(JsonObject jsonObj) throws Exception {
        return (jsonObj.get(TASK_FIELD_DEADLINE).getAsString());
    }

    public static String getJsonPropertyFrom(JsonObject jsonObj) throws Exception {
        return (jsonObj.get(TASK_FIELD_FROM).getAsString());
    }

    public static String getJsonPropertyTo(JsonObject jsonObj) throws Exception {
        return (jsonObj.get(TASK_FIELD_TO).getAsString());
    }

    public static Integer getJsonPropertyTaskId(JsonObject jsonObj) {
        return jsonObj.get(TASK_FIELD_TASK_ID).getAsInt();
    }

    private JsonTaskToObjectParser() {
    }
}
