package duke.command.systemCommand;

import com.google.gson.JsonParser;
import duke.command.CommandJsonResponse;
import duke.dukeUtility.enums.ResponseType;

import java.io.Reader;
import java.nio.file.Path;
import java.util.List;

public class CommandReadTasks extends CommandJsonResponse {
    public CommandReadTasks(Reader reader, Path path) {
        super(ResponseType.FILE_READ, List.of("json", "tasks",path.toString()), JsonParser.parseReader(reader).getAsJsonArray());
    }
}
