package duke.taskManagerTest;

import static duke.testhelper.help.config.dukeIOTestPath.getDefaultTasksTestExportPathString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.google.gson.JsonArray;
import duke.FileResourceManager;
import duke.TaskManager;
import duke.command.Command;
import duke.command.CommandJsonResponse;
import duke.dukeUtility.enums.ResponseType;
import duke.testhelper.TestStream;


public class ImportTasksTest extends TestStream {


    @Test
    public void AddToDos_Export_Extract_Load_Test() {
        TaskManager tm1 = new TaskManager();

        String desc0 = "desc0";
        String desc1 = "desc1";
        tm1.addNewToDo(desc0);
        tm1.addNewToDo(desc1);
        assertEquals(desc0, tm1.getTaskById(0).getTaskDescription());
        assertEquals(desc1, tm1.getTaskById(1).getTaskDescription());

        String exportPathString = getDefaultTasksTestExportPathString();
        String importPathString = exportPathString;
        FileResourceManager frm = new FileResourceManager(exportPathString, importPathString);

        // export
        Command c = frm.executeCommandSave(tm1);
        assertEquals(ResponseType.FILE_SAVED, c.getResponseType());
        // extract
        CommandJsonResponse reading = frm.executeExtractTasksFromFile();
        assertEquals(ResponseType.FILE_READ, reading.getResponseType());
        // load
        JsonArray tasksFromFile = (JsonArray) reading.getJsonArg();

        TaskManager tm2 = new TaskManager();
        frm.importTasksJson(tasksFromFile, tm2);

        assertEquals(tm2.getTaskById(0).getTaskDescription(), tm1.getTaskById(0).getTaskDescription());
        assertEquals(tm2.getTaskById(1).getTaskDescription(), tm1.getTaskById(1).getTaskDescription());
    }
}
