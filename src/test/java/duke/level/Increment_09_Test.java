package duke.level;


import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.mock.mockTask.MockTask;
import duke.mock.mockTask.MockToDo;
import duke.testHelper.TestStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static duke.testHelper.help.CodeUnderTest.OutputUnderTest.*;
import static duke.testHelper.help.CodeUnderTest.PrettifyUnderTest.getExpectedTaskList;
import static duke.testHelper.help.CodeUnderTest.TextCommandUnderTest.*;
import static duke.testHelper.help.CodeUnderTest.TextCommandUnderTest.PROMPT_UNDER_TEST_EXIT_LOOP;
import static duke.testHelper.help.config.dukeIOTestPath.getDefaultTasksImportTestPathString;
import static duke.testHelper.help.config.dukeIOTestPath.getDefaultTasksTestExportPathString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Increment_09_Test extends TestStream {
    @Test
    public void Greet_AddToDo_ListTasksWithKeyword_Save_Exit() {

        String keyword = "MAGIK";
        String taskDesc0 = "nons afasf09qhy2gr";
        String taskDesc1 = "aasfg " + keyword + " c124124";
        String store0Command= generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc0);
        String store1Command= generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc1);
        String findCommand = generateTextCommandFindKeywordInDescription(PROMPT_UNDER_TEST_FIND,keyword);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        StringBuilder commandBuilder = new StringBuilder();

        commandBuilder.append(store0Command);
        commandBuilder.append(store1Command);
        commandBuilder.append(findCommand);
        commandBuilder.append(exitCommand);
        System.setIn(new ByteArrayInputStream(commandBuilder.toString().getBytes()));


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


        StringBuilder expectedResponseBuilder = new StringBuilder();

        MockToDo expectedToDo1 = new MockToDo(taskDesc1, 1, false);

        MockTask[] MockTasks = {expectedToDo1}; // only task 1 should be displayed after query
        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());

        expectedResponseBuilder.append(getExpectedOutputEntry());
        expectedResponseBuilder.append(getExpectedOutputImportAttempt(frm.getImportPath()));
        expectedResponseBuilder.append(getExpectedOutputReadPathNotFound());
        expectedResponseBuilder.append(getExpectedOutputBeginInputLoop());

        expectedResponseBuilder.append(getExpectedOutputAddedToDo(taskDesc0));
        expectedResponseBuilder.append(getExpectedOutputAddedToDo(taskDesc1));

        expectedResponseBuilder.append(getExpectedOutputList(getExpectedTaskList(MockTasks)));
        expectedResponseBuilder.append(getExpectedOutputExitInputLoop());
        expectedResponseBuilder.append(getExpectedOutputTerminate());
        String expectedOutputResponse = expectedResponseBuilder.toString();


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

        StringBuilder commandBuilder = new StringBuilder();

        commandBuilder.append(store0Command);
        commandBuilder.append(findCommand);
        commandBuilder.append(exitCommand);
        System.setIn(new ByteArrayInputStream(commandBuilder.toString().getBytes()));


        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added todo0" message
         * queried tasks list
         * exit loop
         * terminate
         */

        StringBuilder expectedResponseBuilder = new StringBuilder();


        MockTask[] MockTasks = {}; // no should be displayed after query
        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());

        expectedResponseBuilder.append(getExpectedOutputEntry());
        expectedResponseBuilder.append(getExpectedOutputImportAttempt(frm.getImportPath()));
        expectedResponseBuilder.append(getExpectedOutputReadPathNotFound());
        expectedResponseBuilder.append(getExpectedOutputBeginInputLoop());

        expectedResponseBuilder.append(getExpectedOutputAddedToDo(taskDesc0));

        expectedResponseBuilder.append(getExpectedOutputList(getExpectedTaskList(MockTasks)));
        expectedResponseBuilder.append(getExpectedOutputExitInputLoop());
        expectedResponseBuilder.append(getExpectedOutputTerminate());
        String expectedOutputResponse = expectedResponseBuilder.toString();

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

        StringBuilder commandBuilder = new StringBuilder();

        commandBuilder.append(store0Command);
        commandBuilder.append(findCommand);
        commandBuilder.append(exitCommand);
        System.setIn(new ByteArrayInputStream(commandBuilder.toString().getBytes()));

        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added todo0" message
         * queried tasks list
         * exit loop
         * terminate
         */


        StringBuilder expectedResponseBuilder = new StringBuilder();


        MockTask[] MockTasks = {}; // no should be displayed after query
        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());

        expectedResponseBuilder.append(getExpectedOutputEntry());
        expectedResponseBuilder.append(getExpectedOutputImportAttempt(frm.getImportPath()));
        expectedResponseBuilder.append(getExpectedOutputReadPathNotFound());
        expectedResponseBuilder.append(getExpectedOutputBeginInputLoop());

        expectedResponseBuilder.append(getExpectedOutputAddedToDo(taskDesc0));

        expectedResponseBuilder.append(getMsgUnderTestErrorSpacedKeyword());
        expectedResponseBuilder.append(getExpectedOutputExitInputLoop());
        expectedResponseBuilder.append(getExpectedOutputTerminate());
        String expectedOutputResponse = expectedResponseBuilder.toString();

        try {
            Main.run(this.getPrintStream(), tm,frm);
        } catch (Exception e) {
            fail(e.toString());
        }

        assertEquals(expectedOutputResponse,this.getOutput());
    }


}
