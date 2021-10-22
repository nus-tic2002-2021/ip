package duke.inputOutputTest;

import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.testhelper.TestStream;
import org.junit.jupiter.api.Test;

import static duke.testhelper.help.Builder.*;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.*;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.*;
import static duke.testhelper.help.config.dukeIOTestPath.getDefaultTasksImportTestPathString;
import static duke.testhelper.help.config.dukeIOTestPath.getDefaultTasksTestExportPathString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestIoNegative extends TestStream {

    @Test
    public void TestUnknownCommand() throws Exception {

        String randomTextCommand = generateTextCommandRandom("s0meUnknownPrompt");
        String exitLoopCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);
        System.setIn(buildCommandInputStream(randomTextCommand,exitLoopCommand));

        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());
        String out0 = getExpectedOutputEntry();
        String out1 = getExpectedOutputImportAttempt(frm.getImportPath());
        String out2 = getExpectedOutputReadPathNotFound();
        String out3 = getExpectedOutputBeginInputLoop();
        String out4 = getExpectedOutputCommandUnknown();
        String out5 = getExpectedOutputExitInputLoop();
        String out6 = getExpectedOutputTerminate();

        String expectedOutputResponse = buildExpectedResponse(out0,out1,out2,out3,out4,out5,out6);

        Main.run(this.getPrintStream(), tm,frm);

        assertEquals(expectedOutputResponse, this.getOutput());
    }

}
