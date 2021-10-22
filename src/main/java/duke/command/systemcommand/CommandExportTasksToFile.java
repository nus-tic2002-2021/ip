package duke.command.systemcommand;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonWriter;
import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

import java.util.List;

public class CommandExportTasksToFile extends Command {
    public CommandExportTasksToFile(JsonArray tasksJson, JsonWriter jw) {
        super(ResponseType.EXPORT_IN_PROGRESS, List.of("export", jw.toString()));
        try {
            new Gson().toJson(tasksJson, jw);
            jw.close();
            this.setResponseType(ResponseType.FILE_SAVED);
        } catch (Exception error) {
            this.setResponseType(ResponseType.ERROR_SAVE_FAILURE);
        }
    }
}
