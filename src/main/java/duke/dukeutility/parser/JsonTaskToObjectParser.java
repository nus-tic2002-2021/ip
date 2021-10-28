package duke.dukeutility.parser;

import static duke.dukeutility.definition.TaskField.TASK_FIELD_DEADLINE;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_DESCRIPTION;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_DONE_STATUS;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_FROM;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_TASK_ID;
import static duke.dukeutility.definition.TaskField.TASK_FIELD_TO;
import static duke.dukeutility.parser.DateParser.parseStringAsLocalDateTime;

import java.time.LocalDateTime;

import com.google.gson.JsonObject;

public class JsonTaskToObjectParser extends Parser {
    private JsonTaskToObjectParser() {
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
