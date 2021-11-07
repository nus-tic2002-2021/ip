package duke.command.systemcommand;

import static duke.dukeutility.enums.ResponseType.FILE_SAVED;
import static duke.dukeutility.enums.ResponseType.FILE_SAVED_IO_ERROR;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonWriter;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandExportTasksToFile extends Command {
    /**
     * Export tasks to file as JSON. Will return response type based on the execution.
     *
     * @param tasks task objects to write
     * @param jw    writer
     */
    public CommandExportTasksToFile(JsonArray tasks, Path writePath, JsonWriter jw) {
        super(ResponseType.EXPORT_IN_PROGRESS, List.of("export", writePath.toString()));

        new Gson().toJson(tasks, jw);
        try {
            jw.close();
            this.setResponseType(FILE_SAVED);
        } catch (IOException err) {
            this.setResponseType(FILE_SAVED_IO_ERROR);

        }


    }

    public String getResponse() {
        String reply;
        ResponseType rt = this.getResponseType();
        switch (rt) {
        case FILE_SAVED:
            reply = "Saved task to file: ";
            break;
        case FILE_SAVED_IO_ERROR:
            reply = "File save error . . . ";
            break;
        default:
            reply = "Save did not complete to";

        }
        return reply + this.getArgs().get(1) + System.lineSeparator();
    }
}
