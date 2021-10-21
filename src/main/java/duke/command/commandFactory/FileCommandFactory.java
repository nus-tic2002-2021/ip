package duke.command.commandFactory;

import duke.command.CommandJsonResponse;
import duke.command.errorCommand.CommandReadFileError;
import duke.command.systemCommand.CommandReadTasks;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCommandFactory extends CommandFactory {


    public CommandJsonResponse executeExtractTasksFromFile(Path path) {
        Reader reader;
        try {
            reader = Files.newBufferedReader(path,
                    StandardCharsets.UTF_8);
        } catch (Exception e) {
            return new CommandReadFileError("Invalid file read path. " + e);
        }
        return new CommandReadTasks(reader,path);
    }

}
