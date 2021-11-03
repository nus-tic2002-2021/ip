package duke.command.commandfactory;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import duke.command.CommandJsonResponse;
import duke.command.errorcommand.CommandReadFileError;
import duke.command.systemcommand.CommandReadTasks;

public class FileCommandFactory extends CommandFactory {

    /**
     * Extract tasks from file.
     *
     * @param path file of saved tasks
     * @return command
     */
    public CommandJsonResponse executeExtractTasksFromFile(Path path) {
        Reader reader;
        try {
            reader = Files.newBufferedReader(path,
                StandardCharsets.UTF_8);
        } catch (Exception e) {
            return new CommandReadFileError("Invalid file read path. " + e);
        }
        return new CommandReadTasks(reader, path);
    }
}
