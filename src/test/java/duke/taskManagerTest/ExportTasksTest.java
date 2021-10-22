package duke.taskManagerTest;

import static duke.testhelper.help.config.dukeIOTestPath.getDefaultTasksTestExportPathString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import duke.FileResourceManager;
import duke.TaskManager;
import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;
import duke.testhelper.TestStream;


public class ExportTasksTest extends TestStream {

    @Test
    public void Test_AddToDos_Export_Should_Buffer_Test() {
        TaskManager tm = new TaskManager();

        String desc0 = "desc0";
        String desc1 = "desc1";
        tm.addNewToDo(desc0);
        tm.addNewToDo(desc1);
        assertEquals(desc0, tm.getTaskById(0).getTaskDescription());
        assertEquals(desc1, tm.getTaskById(1).getTaskDescription());

        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(), null);
        Command c = frm.executeCommandSave(tm);
        assertEquals(ResponseType.FILE_SAVED, c.getResponseType());
    }

}
