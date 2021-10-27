package duke.integrationtest;


import static duke.testhelper.help.Builder.buildCommandInputStream;
import static duke.testhelper.help.Builder.buildExpectedResponse;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputAddedToDo;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputBeginInputLoop;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputEntry;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputExitInputLoop;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputImportAttempt;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputListTasksWithKeywordDescription;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputReadPathNotFound;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputTerminate;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getMsgUnderTestErrorSpacedKeyword;
import static duke.testhelper.help.codeundertest.PrettifyUnderTest.getExpectedTaskList;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_EXIT_LOOP;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_FIND;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandExit;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandFindKeywordInDescription;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandLineAddToDo;
import static duke.testhelper.help.config.DukeIoTestPath.getDefaultTasksImportTestPathString;
import static duke.testhelper.help.config.DukeIoTestPath.getDefaultTasksTestExportPathString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.mock.mocktask.MockTask;
import duke.mock.mocktask.MockToDo;
import duke.testhelper.TestStream;

public class TestIoFind extends TestStream {
    /**
     * Test find routine.
     */
    @Test
    public void greet_addToDoListTasksWithKeywordSave_exit() {

        String keyword = "MAGIK";
        String taskDesc0 = "nons afasf09qhy2gr";
        String taskDesc1 = "aasfg " + keyword + " c124124";
        String taskDesc2 = "aasfg " + keyword.toLowerCase() + " negdndetnjd";
        String store0Command = generateTextCommandLineAddToDo(taskDesc0);
        String store1Command = generateTextCommandLineAddToDo(taskDesc1);
        String store2Command = generateTextCommandLineAddToDo(taskDesc2);
        String findCommand = generateTextCommandFindKeywordInDescription(PROMPT_UNDER_TEST_FIND, keyword);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        System.setIn(buildCommandInputStream(store0Command, store1Command, store2Command, findCommand, exitCommand));

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

        MockToDo expectedTask1 = new MockToDo(taskDesc1, 1, false);
        MockToDo expectedTask2 = new MockToDo(taskDesc2, 2, false);

        MockTask[] mockTasks = {expectedTask1, expectedTask2}; // only task 1 should be displayed after query
        TaskManager tm = new TaskManager();

        String importPathString = getDefaultTasksTestExportPathString();
        String exportPathString = getDefaultTasksImportTestPathString();
        FileResourceManager frm = new FileResourceManager(importPathString, exportPathString);

        String out0 = (getExpectedOutputEntry());
        String out1 = (getExpectedOutputImportAttempt(frm.getImportPath()));
        String out2 = (getExpectedOutputReadPathNotFound());
        String out3 = (getExpectedOutputBeginInputLoop());
        String out4 = (getExpectedOutputAddedToDo(taskDesc0, 0));
        String out5 = (getExpectedOutputAddedToDo(taskDesc1, 1));
        String out6 = (getExpectedOutputAddedToDo(taskDesc2, 2));
        String out7 = (getExpectedOutputListTasksWithKeywordDescription(getExpectedTaskList(mockTasks), keyword));
        String out8 = (getExpectedOutputExitInputLoop());
        String out9 = (getExpectedOutputTerminate());
        String expectedOutputResponse =
            buildExpectedResponse(out0, out1, out2, out3, out4, out5, out6, out7, out8, out9);

        try {
            Main.run(this.getPrintStream(), tm, frm);
        } catch (Exception e) {
            fail(e.toString());
        }

        assertEquals(expectedOutputResponse, this.getOutput());
    }

    /**
     * Test find routine.
     */
    @Test
    public void greet_addToDoListTasksWithKeywordSaveExit_emptyResult() {

        String keyword = "MAGIK";
        String taskDesc0 = "nons afasf09qhy2gr";
        String store0Command = generateTextCommandLineAddToDo(taskDesc0);
        String findCommand = generateTextCommandFindKeywordInDescription(PROMPT_UNDER_TEST_FIND, keyword);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        System.setIn(buildCommandInputStream(store0Command, findCommand, exitCommand));

        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added todo0" message
         * queried tasks list
         * exit loop
         * terminate
         */


        MockTask[] mockTasks = {}; // no should be displayed after query
        TaskManager tm = new TaskManager();
        FileResourceManager frm =
            new FileResourceManager(getDefaultTasksTestExportPathString(), getDefaultTasksImportTestPathString());

        String out0 = (getExpectedOutputEntry());
        String out1 = (getExpectedOutputImportAttempt(frm.getImportPath()));
        String out2 = (getExpectedOutputReadPathNotFound());
        String out3 = (getExpectedOutputBeginInputLoop());
        String out4 = (getExpectedOutputAddedToDo(taskDesc0, 0));
        String out5 = (getExpectedOutputListTasksWithKeywordDescription(getExpectedTaskList(mockTasks), keyword));
        String out6 = (getExpectedOutputExitInputLoop());
        String out7 = (getExpectedOutputTerminate());
        String expectedOutputResponse = buildExpectedResponse(out0, out1, out2, out3, out4, out5, out6, out7);

        try {
            Main.run(this.getPrintStream(), tm, frm);
        } catch (Exception e) {
            fail(e.toString());
        }
        assertEquals(expectedOutputResponse, this.getOutput());
    }

    /**
     * Test find routine with invalid keyword. Should not execute the search.
     */
    @Test
    public void greet_addToDoListTasksWithInvalidKeywordSave_exit() {

        String keyword = "MAGIK asfasf";
        String taskDesc0 = "nons afasf09qhy2gr";
        String store0Command = generateTextCommandLineAddToDo(taskDesc0);
        String findCommand = generateTextCommandFindKeywordInDescription(PROMPT_UNDER_TEST_FIND, keyword);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        System.setIn(buildCommandInputStream(store0Command, findCommand, exitCommand));

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
        FileResourceManager frm =
            new FileResourceManager(getDefaultTasksTestExportPathString(), getDefaultTasksImportTestPathString());

        String out0 = (getExpectedOutputEntry());
        String out1 = (getExpectedOutputImportAttempt(frm.getImportPath()));
        String out2 = (getExpectedOutputReadPathNotFound());
        String out3 = (getExpectedOutputBeginInputLoop());
        String out4 = (getExpectedOutputAddedToDo(taskDesc0, 0));
        String out5 = (getMsgUnderTestErrorSpacedKeyword());
        String out6 = (getExpectedOutputExitInputLoop());
        String out7 = (getExpectedOutputTerminate());
        String expectedOutputResponse = buildExpectedResponse(out0, out1, out2, out3, out4, out5, out6, out7);

        try {
            Main.run(this.getPrintStream(), tm, frm);
        } catch (Exception e) {
            fail(e.toString());
        }

        assertEquals(expectedOutputResponse, this.getOutput());
    }


}
