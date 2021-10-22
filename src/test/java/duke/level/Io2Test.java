package duke.level;

import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.mock.mockTask.MockTask;
import duke.mock.mockTask.MockToDo;
import duke.testHelper.TestStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static duke.testHelper.help.Builder.buildCommandInputStream;
import static duke.testHelper.help.Builder.buildExpectedResponse;
import static duke.testHelper.help.CodeUnderTest.OutputUnderTest.*;
import static duke.testHelper.help.CodeUnderTest.PrettifyUnderTest.getPrettifyUnderTestList;
import static duke.testHelper.help.CodeUnderTest.TextCommandUnderTest.*;
import static duke.testHelper.help.config.dukeIOTestPath.getDefaultTasksImportTestPathString;
import static duke.testHelper.help.config.dukeIOTestPath.getDefaultTasksTestExportPathString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Io2Test extends TestStream {

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

        String store0Command= generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc0);
        String delete0Command = generateTextCommandDeleteTaskByTaskId(PROMPT_UNDER_TEST_DELETE_TASK, 0);
        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        System.setIn(buildCommandInputStream(store0Command,delete0Command,listCommand,exitCommand));

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
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());
        MockTask[] MockTasks = {}; // since task is deleted
        String out0 = (getMsgUnderTestEntry());
        String out1 = (getMsgUnderTestAttemptImport(frm.getImportPath()));
        String out2 = (getMsgUnderTestReadPathNotFound());
        String out3 = (getMsgUnderTestBeginInputLoop());
        String out4 = (getMsgUnderTestResponseToDoAdded(taskDesc0));
        String out5 = (getMsgUnderTestResponseTaskDeleted(0));
        String out6 = (getMsgUnderTestResponseList(getPrettifyUnderTestList(MockTasks)));
        String out7 = (getMsgUnderTestExitLoop());
        String out8 = (getMsgUnderTestTerminate());

        String expectedOutputResponse = buildExpectedResponse(out0, out1, out2, out3, out4, out5, out6, out7, out8);
        Main.run(this.getPrintStream(), tm,frm);
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

        String store0Command= generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc0);
        String store1Command= generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc1);
        String delete0Command = generateTextCommandDeleteTaskByTaskId(PROMPT_UNDER_TEST_DELETE_TASK, 0);
        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        System.setIn(buildCommandInputStream(store0Command,store1Command,delete0Command,listCommand,exitCommand));

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
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());
        String out0 = (getMsgUnderTestEntry());
        String out1 = (getMsgUnderTestAttemptImport(frm.getImportPath()));
        String out2 = (getMsgUnderTestReadPathNotFound());
        String out3 = (getMsgUnderTestBeginInputLoop());
        String out4 = (getMsgUnderTestResponseToDoAdded(taskDesc0));
        String out5 = (getMsgUnderTestResponseToDoAdded(taskDesc1));
        String out6 = (getMsgUnderTestResponseTaskDeleted(0));
        String out7 = (getMsgUnderTestResponseList(getPrettifyUnderTestList(MockTasks)));
        String out8 = (getMsgUnderTestExitLoop());
        String out9 = (getMsgUnderTestTerminate());
        String expectedOutputResponse = buildExpectedResponse(out0,out1,out2,out3,out4,out5,out6,out7,out8,out9);
        Main.run(this.getPrintStream(), tm,frm);
        assertEquals(expectedOutputResponse, this.getOutput());
    }


}
