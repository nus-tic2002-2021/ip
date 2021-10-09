package duke;

import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;
import duke.testHelper.TestStream;
import org.junit.jupiter.api.Test;

import static duke.testHelper.help.config.dukeIOTestPath.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ExportTasks_Test extends TestStream {

    @Test
    public void Test_AddToDos_Export_Should_Buffer_Test(){
        TaskManager tm = new TaskManager();

        String desc0 = "desc0";
        String desc1 = "desc1";
        tm.addNewToDo(desc0);
        tm.addNewToDo(desc1);
        assertEquals(desc0, tm.getTaskById(0).getTaskDescription());
        assertEquals(desc1, tm.getTaskById(1).getTaskDescription());

        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),null);
        Command c = frm.executeCommandSave(tm);
        assertEquals(ResponseType.FILE_SAVED,c.getResponseType());
    }

}
