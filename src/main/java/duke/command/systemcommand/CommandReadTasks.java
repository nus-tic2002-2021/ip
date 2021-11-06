package duke.command.systemcommand;

import java.io.Reader;
import java.nio.file.Path;
import java.util.List;
import com.google.gson.JsonParser;
import duke.command.CommandJsonResponse;
import duke.dukeutility.enums.ResponseType;

public class CommandReadTasks extends CommandJsonResponse {
    /**
     * get tasks from reader and parse as json
     *
     * @param reader reader
     * @param path   path
     */
    public CommandReadTasks(Reader reader, Path path) {
        super(ResponseType.FILE_READ, List.of("json", "tasks", path.toString()),
            JsonParser.parseReader(reader).getAsJsonArray());
    }

    public String getResponse() {
        return "Success reading file " + this.getArgs().get(0) + System.lineSeparator();
    }
}
