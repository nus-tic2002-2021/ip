package duke.level;

import com.google.gson.JsonArray;
import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.command.commandFactory.FileCommandFactory;
import duke.testHelper.TestStream;
import duke.testHelper.help.config.dukeIOTestPath;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static duke.testHelper.help.TextCommandUnderTest.*;
import static duke.testHelper.help.config.dukeIOTestPath.resourceTestFolder;
import static org.junit.jupiter.api.Assertions.*;

public class Increment_07_Test extends TestStream {
    @Test
    public void Greet_AddToDo_Delete_List_Save_Exit() throws Exception {
        String thisTestSign = "saveEventsToJsonFile";
        String export1PathString = resourceTestFolder + 1 + thisTestSign + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss")) + ".json";
        String export2PathString = resourceTestFolder + 2 + thisTestSign + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss")) + ".json";
        FileResourceManager frm1 = new FileResourceManager(export1PathString, null);
        TaskManager tm1 = new TaskManager();
        StringBuilder commandLines = new StringBuilder();
        //  sets of add tasks textCommands
        int countPerTaskType = 10;
        int expectedTotalTaskCount = 0;
        for (int i = 1; i <= countPerTaskType; i++) {
            commandLines.append("todo todo" + i + System.lineSeparator());
            expectedTotalTaskCount++;
            commandLines.append("deadline deadline /by 202002" + String.format("%2s", 19) + System.lineSeparator());
            expectedTotalTaskCount++;
            commandLines.append("event e /at 20200202-20200203" + System.lineSeparator());
            expectedTotalTaskCount++;
        }
        String setTask5DoneCommand = generateTextCommandSetCompleted(PROMPT_UNDER_TEST_MARK_AS_DONE,5);
        commandLines.append(setTask5DoneCommand);
        commandLines.append(generateTextCommandSave(PROMPT_UNDER_TEST_SAVE));
        commandLines.append(generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP));
        System.setIn(new ByteArrayInputStream(commandLines.toString().getBytes()));
        try {
            Main.run(this.getPrintStream(), tm1,frm1);
            assertSame(tm1.getSize(),expectedTotalTaskCount,"expected amount " + expectedTotalTaskCount
                    + ", actual " + tm1.getSize() + System.lineSeparator());
        } catch (Exception e) {
            fail(e.toString());
        }


        commandLines = new StringBuilder();
        commandLines.append(generateTextCommandSave(PROMPT_UNDER_TEST_SAVE));
        commandLines.append(generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP));
        System.setIn(new ByteArrayInputStream(commandLines.toString().getBytes()));
        FileResourceManager frm2 = new FileResourceManager(export2PathString, export1PathString);

        TaskManager tm2 = new TaskManager();
        try {
            Main.run(this.getPrintStream(), tm2 ,frm2);
        } catch (Exception e) {
            fail(e.toString());
        }
        assertEquals(30,tm2.getSize());
        try {
            JsonArray export1 = new FileCommandFactory().executeExtractTasksFromFile(frm1.getExportPath()).getJsonArg().getAsJsonArray();
            JsonArray export2 = new FileCommandFactory().executeExtractTasksFromFile(frm2.getExportPath()).getJsonArg().getAsJsonArray();
            assertNotNull(export1);
            assertNotNull(export2);
            assertEquals(30,export1.size());
            assertEquals(30,export2.size());
            assertEquals(export1, export2);
        } catch (Exception e) {
            fail("Failure during comparing exports. " + e + this.getOutput());
        }
    }

    @Test
    public void GenerateExpectedTestFileInActualEnvironment() throws Exception {
        String importPathString = dukeIOTestPath.getDefaultTasksImportPathString();
        String exportPathString = System.getProperty("user.dir") + File.separator +"src" + File.separator + "test" + File.separator +"resources" + File.separator +"linux-test" + File.separator + "do-not-use.txt";
        FileResourceManager frm1 = new FileResourceManager(exportPathString, importPathString);
        TaskManager tm1 = new TaskManager();
        Scanner in = new Scanner(new FileReader(System.getProperty("user.dir") + File.separator +"src" + File.separator + "test" + File.separator +"resources" + File.separator +"linux-test" + File.separator + "input.txt"));
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()) {
            sb.append(in.nextLine());
            sb.append(System.lineSeparator());
        }
        in.close();
        System.setIn(new ByteArrayInputStream(sb.toString().getBytes()));
        this.setPrintStream(new PrintStream(System.getProperty("user.dir") + File.separator +"src" + File.separator + "test" + File.separator +"resources" + File.separator +"linux-test" + File.separator + "expected.txt"));
        try {
            Main.run(this.getPrintStream(), tm1,frm1);
        } catch (Exception e) {
            fail(e.toString());
        }
    }
}
