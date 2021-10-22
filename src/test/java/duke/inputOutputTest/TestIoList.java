package duke.inputOutputTest;

import static duke.testhelper.help.Builder.buildCommandInputStream;
import static duke.testhelper.help.Builder.buildExpectedResponse;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputAddedToDo;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputBeginInputLoop;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputCommandDeleted;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputEntry;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputExitInputLoop;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputImportAttempt;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputList;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputReadPathNotFound;
import static duke.testhelper.help.CodeUnderTest.OutputUnderTest.getExpectedOutputTerminate;
import static duke.testhelper.help.CodeUnderTest.PrettifyUnderTest.getExpectedTaskList;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.PROMPT_UNDER_TEST_ADD_TO_DO;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.PROMPT_UNDER_TEST_DELETE_TASK;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.PROMPT_UNDER_TEST_EXIT_LOOP;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.PROMPT_UNDER_TEST_LIST;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.generateTextCommandDeleteTaskByTaskId;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.generateTextCommandExit;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.generateTextCommandLineAddToDo;
import static duke.testhelper.help.CodeUnderTest.TextCommandUnderTest.generateTextCommandList;
import static duke.testhelper.help.config.dukeIOTestPath.getDefaultTasksImportTestPathString;
import static duke.testhelper.help.config.dukeIOTestPath.getDefaultTasksTestExportPathString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.mock.mockTask.MockTask;
import duke.mock.mockTask.MockToDo;
import duke.testhelper.TestStream;

public class TestIoList extends TestStream {

    @Test
    public void Greet_AddThenDeleteToDoList_Exit() throws Exception {

        /* Arrange Input
         * Commands executed:
         *
         * add task 0 with task description
         * delete task 0
         * list
         * exit loop
         */


        String taskDesc0 = "taskDesc abc";

        String store0Command = generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc0);
        String delete0Command = generateTextCommandDeleteTaskByTaskId(PROMPT_UNDER_TEST_DELETE_TASK, 0);
        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        System.setIn(buildCommandInputStream(store0Command, delete0Command, listCommand, exitCommand));

        /* Arrange Expected Output
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
        MockTask[] MockTasks = {}; // since task is deleted
        String out0 = (getExpectedOutputEntry());
        String out1 = (getExpectedOutputImportAttempt(frm.getImportPath()));
        String out2 = (getExpectedOutputReadPathNotFound());
        String out3 = (getExpectedOutputBeginInputLoop());
        String out4 = (getExpectedOutputAddedToDo(taskDesc0));
        String out5 = (getExpectedOutputCommandDeleted(0));
        String out6 = (getExpectedOutputList(getExpectedTaskList(MockTasks)));
        String out7 = (getExpectedOutputExitInputLoop());
        String out8 = (getExpectedOutputTerminate());

        String expectedOutputResponse = buildExpectedResponse(out0, out1, out2, out3, out4, out5, out6, out7, out8);
        Main.run(this.getPrintStream(), tm, frm);
        assertEquals(expectedOutputResponse, this.getOutput());
    }


    @Test
    public void Greet_Add2ToDosDeleteList_Exit() throws Exception {

        /*
         * Commands executed:
         *
         * add task 0 with task description
         * add task 1 with task description
         * delete task 0
         * list
         * exit loop
         */

        String taskDesc0 = "taskDesc abc";
        String taskDesc1 = "taskDesc def";

        String store0Command = generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc0);
        String store1Command = generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc1);
        String delete0Command = generateTextCommandDeleteTaskByTaskId(PROMPT_UNDER_TEST_DELETE_TASK, 0);
        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        System.setIn(buildCommandInputStream(store0Command, store1Command, delete0Command, listCommand, exitCommand));

        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added todo" message
         * tabled tasks list
         * exit loop
         * terminate
         */
        MockToDo expectedToDo1 = new MockToDo(taskDesc1, 1, false);
        MockTask[] MockTasks = {expectedToDo1}; // since task0 is deleted

        TaskManager tm = new TaskManager();
        FileResourceManager frm =
            new FileResourceManager(getDefaultTasksTestExportPathString(), getDefaultTasksImportTestPathString());
        String out0 = (getExpectedOutputEntry());
        String out1 = (getExpectedOutputImportAttempt(frm.getImportPath()));
        String out2 = (getExpectedOutputReadPathNotFound());
        String out3 = (getExpectedOutputBeginInputLoop());
        String out4 = (getExpectedOutputAddedToDo(taskDesc0));
        String out5 = (getExpectedOutputAddedToDo(taskDesc1));
        String out6 = (getExpectedOutputCommandDeleted(0));
        String out7 = (getExpectedOutputList(getExpectedTaskList(MockTasks)));
        String out8 = (getExpectedOutputExitInputLoop());
        String out9 = (getExpectedOutputTerminate());
        String expectedOutputResponse =
            buildExpectedResponse(out0, out1, out2, out3, out4, out5, out6, out7, out8, out9);
        Main.run(this.getPrintStream(), tm, frm);
        assertEquals(expectedOutputResponse, this.getOutput());
    }


}
