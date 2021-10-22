package duke.level;


import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.mock.mockTask.MockTask;
import duke.mock.mockTask.MockToDo;
import duke.testHelper.TestStream;
import org.junit.jupiter.api.Test;

import static duke.testHelper.help.Builder.buildCommandInputStream;
import static duke.testHelper.help.Builder.buildExpectedResponse;
import static duke.testHelper.help.CodeUnderTest.OutputUnderTest.*;
import static duke.testHelper.help.CodeUnderTest.PrettifyUnderTest.getExpectedTaskList;
import static duke.testHelper.help.CodeUnderTest.TextCommandUnderTest.*;
import static duke.testHelper.help.CodeUnderTest.TextCommandUnderTest.PROMPT_UNDER_TEST_EXIT_LOOP;
import static duke.testHelper.help.config.dukeIOTestPath.getDefaultTasksImportTestPathString;
import static duke.testHelper.help.config.dukeIOTestPath.getDefaultTasksTestExportPathString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestIoFind extends TestStream {
    @Test
    public void Greet_AddToDo_ListTasksWithKeyword_Save_Exit() {

        String keyword = "MAGIK";
        String taskDesc0 = "nons afasf09qhy2gr";
        String taskDesc1 = "aasfg " + keyword + " c124124";
        String store0Command= generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc0);
        String store1Command= generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc1);
        String findCommand = generateTextCommandFindKeywordInDescription(PROMPT_UNDER_TEST_FIND,keyword);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        System.setIn(buildCommandInputStream(store0Command,store1Command,findCommand,exitCommand));


        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added todo0" message
         * "Added todo0" message
         * queried tasks list
         * exit loop
         * terminate
         */

        MockToDo expectedToDo1 = new MockToDo(taskDesc1, 1, false);

        MockTask[] MockTasks = {expectedToDo1}; // only task 1 should be displayed after query
        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());

        String out0 = (getExpectedOutputEntry());
        String out1 = (getExpectedOutputImportAttempt(frm.getImportPath()));
        String out2 = (getExpectedOutputReadPathNotFound());
        String out3 = (getExpectedOutputBeginInputLoop());
        String out4 = (getExpectedOutputAddedToDo(taskDesc0));
        String out5 = (getExpectedOutputAddedToDo(taskDesc1));
        String out6 = (getExpectedOutputList(getExpectedTaskList(MockTasks)));
        String out7 = (getExpectedOutputExitInputLoop());
        String out8 = (getExpectedOutputTerminate());
        String expectedOutputResponse = buildExpectedResponse(out0,out1,out2,out3,out4,out5,out6,out7,out8);

        try {
            Main.run(this.getPrintStream(), tm,frm);
        } catch (Exception e) {
            fail(e.toString());
        }

        assertEquals(expectedOutputResponse,this.getOutput());
    }

    @Test
    public void Greet_AddToDo_ListTasksWithKeyword_Save_Exit_Empty_Result()  {

        String keyword = "MAGIK";
        String taskDesc0 = "nons afasf09qhy2gr";
        String store0Command= generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc0);
        String findCommand = generateTextCommandFindKeywordInDescription(PROMPT_UNDER_TEST_FIND,keyword);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        System.setIn(buildCommandInputStream(store0Command,findCommand,exitCommand));

        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added todo0" message
         * queried tasks list
         * exit loop
         * terminate
         */


        MockTask[] MockTasks = {}; // no should be displayed after query
        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());

        String out0 = (getExpectedOutputEntry());
        String out1 = (getExpectedOutputImportAttempt(frm.getImportPath()));
        String out2 = (getExpectedOutputReadPathNotFound());
        String out3 = (getExpectedOutputBeginInputLoop());
        String out4 = (getExpectedOutputAddedToDo(taskDesc0));
        String out5 = (getExpectedOutputList(getExpectedTaskList(MockTasks)));
        String out6 = (getExpectedOutputExitInputLoop());
        String out7 = (getExpectedOutputTerminate());
        String expectedOutputResponse = buildExpectedResponse(out0,out1,out2,out3,out4,out5,out6,out7);

        try {
            Main.run(this.getPrintStream(), tm,frm);
        } catch (Exception e) {
            fail(e.toString());
        }
        assertEquals(expectedOutputResponse,this.getOutput());
    }

    @Test
    public void Greet_AddToDo_ListTasksWithInvalidKeyword_Save_Exit()  {

        String keyword = "MAGIK asfasf";
        String taskDesc0 = "nons afasf09qhy2gr";
        String store0Command= generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc0);
        String findCommand = generateTextCommandFindKeywordInDescription(PROMPT_UNDER_TEST_FIND,keyword);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        System.setIn(buildCommandInputStream(store0Command,findCommand,exitCommand));

        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added todo0" message
         * queried tasks list
         * exit loop
         * terminate
         */

        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());

        String out0 = (getExpectedOutputEntry());
        String out1 = (getExpectedOutputImportAttempt(frm.getImportPath()));
        String out2 = (getExpectedOutputReadPathNotFound());
        String out3 = (getExpectedOutputBeginInputLoop());
        String out4 = (getExpectedOutputAddedToDo(taskDesc0));
        String out5 = (getMsgUnderTestErrorSpacedKeyword());
        String out6 = (getExpectedOutputExitInputLoop());
        String out7 = (getExpectedOutputTerminate());
        String expectedOutputResponse = buildExpectedResponse(out0,out1,out2,out3,out4,out5,out6,out7);

        try {
            Main.run(this.getPrintStream(), tm,frm);
        } catch (Exception e) {
            fail(e.toString());
        }

        assertEquals(expectedOutputResponse,this.getOutput());
    }


}
