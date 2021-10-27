package duke.integrationtest;

import static duke.testhelper.help.Builder.buildCommandInputStream;
import static duke.testhelper.help.Builder.buildExpectedResponse;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputAddedToDo;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputBeginInputLoop;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputEntry;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputExitInputLoop;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputImportAttempt;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputList;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputReadPathNotFound;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputTaskSetCompleted;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputTaskSetIncomplete;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputTerminate;
import static duke.testhelper.help.codeundertest.PrettifyUnderTest.getExpectedTaskList;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_ADD_TO_DO;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_EXIT_LOOP;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_LIST;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_MARK_AS_DONE;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_MARK_AS_INCOMPLETE;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandExit;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandLineAddToDo;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandList;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandSetCompleted;
import static duke.testhelper.help.config.DukeIoTestPath.getDefaultTasksImportTestPathString;
import static duke.testhelper.help.config.DukeIoTestPath.getDefaultTasksTestExportPathString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.mock.mocktask.MockTask;
import duke.mock.mocktask.MockToDo;
import duke.testhelper.TestStream;

public class TestIoToggleDoneStatus extends TestStream {

    @Test
    public void greet_addToDoToggleList_shouldBeSame() throws Exception {

        // Arrange Input

        /*
         * Commands executed:
         *
         * add task 0 with task description
         * list
         * exit loop
         */

        // Arrange Expected Output
        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added todo" message
         * tabled tasks list
         * exit loop
         * terminate
         */

        TaskManager tm = new TaskManager();
        FileResourceManager frm =
            new FileResourceManager(getDefaultTasksTestExportPathString(), getDefaultTasksImportTestPathString());


        String taskDesc0 = "taskDesc abc";

        String out0 = getExpectedOutputEntry();
        String out1 = getExpectedOutputImportAttempt(frm.getImportPath());
        String out2 = getExpectedOutputReadPathNotFound();
        String out3 = getExpectedOutputBeginInputLoop();



        String storeCommand = generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc0);
        String out4 = getExpectedOutputAddedToDo(taskDesc0, 0);


        String setCompleteCommand = generateTextCommandSetCompleted(PROMPT_UNDER_TEST_MARK_AS_DONE, 0);
        String out5 = getExpectedOutputTaskSetCompleted(0);

        String setIncompleteCommand = generateTextCommandSetCompleted(PROMPT_UNDER_TEST_MARK_AS_INCOMPLETE, 0);
        String out6 = getExpectedOutputTaskSetIncomplete(0);

        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);

        MockToDo expectedToDo1 = new MockToDo(taskDesc0, 0, false);
        MockTask[] mockTasks = {expectedToDo1};
        String out7 = getExpectedOutputList(getExpectedTaskList(mockTasks));

        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);
        String out8 = getExpectedOutputExitInputLoop();

        String out9 = getExpectedOutputTerminate();


        System.setIn(buildCommandInputStream(storeCommand, setCompleteCommand, setIncompleteCommand, listCommand, exitCommand));

        String expectedOutputResponse = buildExpectedResponse(out0, out1, out2, out3, out4, out5, out6, out7, out8, out9);

        // Act
        Main.run(this.getPrintStream(), tm, frm);
        // Assert
        assertEquals(expectedOutputResponse, this.getOutput());
    }

}
