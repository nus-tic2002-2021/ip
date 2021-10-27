package duke.integrationtest;

import static duke.testhelper.help.Builder.buildCommandInputStream;
import static duke.testhelper.help.Builder.buildExpectedResponse;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputBeginInputLoop;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputEntry;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputExitInputLoop;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputImportAttempt;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputReadPathNotFound;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputSaved;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputTerminate;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_EXIT_LOOP;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_SAVE;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandExit;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandSave;
import static duke.testhelper.help.config.DukeIoTestPath.getDefaultTasksImportTestPathString;
import static duke.testhelper.help.config.DukeIoTestPath.getDefaultTasksTestExportPathString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.testhelper.TestStream;

public class TestIoSave extends TestStream {

    @Test
    public void saveMessage() throws Exception {
        String saveCommand = generateTextCommandSave(PROMPT_UNDER_TEST_SAVE);
        String exitLoopCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);
        System.setIn(buildCommandInputStream(saveCommand, exitLoopCommand));

        TaskManager tm = new TaskManager();

        String exportPath = getDefaultTasksTestExportPathString();
        FileResourceManager frm = new FileResourceManager(exportPath, getDefaultTasksImportTestPathString());
        String out0 = getExpectedOutputEntry();
        String out1 = getExpectedOutputImportAttempt(frm.getImportPath());
        String out2 = getExpectedOutputReadPathNotFound();
        String out3 = getExpectedOutputBeginInputLoop();
        String out4 = getExpectedOutputSaved(exportPath);
        String out5 = getExpectedOutputExitInputLoop();
        String out6 = getExpectedOutputTerminate();

        String expectedOutputResponse = buildExpectedResponse(out0, out1, out2, out3, out4, out5, out6);

        Main.run(this.getPrintStream(), tm, frm);

        assertEquals(expectedOutputResponse, this.getOutput());
    }

}
