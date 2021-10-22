package duke.level;

import com.google.gson.JsonArray;
import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.command.commandFactory.FileCommandFactory;
import duke.testHelper.TestStream;
import duke.testHelper.help.config.dukeIOTestPath;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static duke.testHelper.help.Builder.buildCommandInputStream;
import static duke.testHelper.help.CodeUnderTest.TextCommandUnderTest.*;
import static duke.testHelper.help.config.dukeIOTestPath.resourceTestFolder;
import static org.junit.jupiter.api.Assertions.*;

public class ExportTest extends TestStream {

    /**
     * Execute add commands and save as export1
     * import export1, save as export2
     * export1 == export2 ? pass
     */
    @Test
    public void IdempotentExport() {
        String thisTestSign = "saveEventsToJsonFile";
        String export1PathString = resourceTestFolder + "-added" + thisTestSign + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss")) + ".json";
        String export2PathString = resourceTestFolder + "-load" + thisTestSign + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss")) + ".json";
        FileResourceManager frm1 = new FileResourceManager(export1PathString, null);
        TaskManager tm1 = new TaskManager();
        //  sets of add tasks textCommands
        int countPerTaskType = 10;
        int expectedTotalTaskCount = 0;

        String[] addCommands = new String[countPerTaskType];
        for (int i = 0; i < countPerTaskType; i++) {

            String todoDesc = "tododesc" + i;

            String deadlineDesc = "deadlinedesc";
            String deadlineBy = "202002"  + String.format("%2s", 19);

            String eventDesc = "eventdesc";
            String eventFrom = "20200202";
            String eventTo = "20210202";

            String addToDoCommand = generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, todoDesc);
            expectedTotalTaskCount++;
            String addDeadlineCommand = generateTextCommandLineAddDeadline(PROMPT_UNDER_TEST_ADD_DEADLINE, deadlineDesc,DELIMITER_DEADLINE_DEADLINE,deadlineBy);
            expectedTotalTaskCount++;
            String addEventCommand = generateTextCommandLineAddEvent(PROMPT_UNDER_TEST_ADD_EVENT, eventDesc, DELIMITER_EVENT_FROM, eventFrom, DELIMITER_EVENT_TO, eventTo);
            expectedTotalTaskCount++;

            addCommands[i] = addToDoCommand + addDeadlineCommand + addEventCommand;
        }

        String in0 = String.join("",addCommands);
        String in1 = generateTextCommandSetCompleted(PROMPT_UNDER_TEST_MARK_AS_DONE,5);
        String in2 = (generateTextCommandSave(PROMPT_UNDER_TEST_SAVE));
        String in3 = (generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP));
        System.setIn(buildCommandInputStream(in0,in1,in2,in3));
        try {
            Main.run(this.getPrintStream(), tm1,frm1);
            assertSame(tm1.getSize(),expectedTotalTaskCount,"expected amount " + expectedTotalTaskCount
                    + ", actual " + tm1.getSize() + System.lineSeparator());
        } catch (Exception e) {
            fail(e.toString());
        }

        String secondIn0 = generateTextCommandSave(PROMPT_UNDER_TEST_SAVE);
        String secondIn1 = generateTextCommandSave(PROMPT_UNDER_TEST_EXIT_LOOP);

        System.setIn(buildCommandInputStream(secondIn0,secondIn1));
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
