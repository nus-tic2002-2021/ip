package duke.level;

import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.mock.mockTask.MockDeadline;
import duke.mock.mockTask.MockEvent;
import duke.mock.mockTask.MockTask;
import duke.mock.mockTask.MockToDo;
import duke.testHelper.TestStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static duke.testHelper.help.OutputUnderTest.*;
import static duke.testHelper.help.PrettifyUnderTest.getPrettifyUnderTestList;
import static duke.testHelper.help.TextCommandUnderTest.*;
import static duke.testHelper.help.config.dukeIOTestPath.getDefaultTasksImportTestPathString;
import static duke.testHelper.help.config.dukeIOTestPath.getDefaultTasksTestExportPathString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * ToDos: tasks without any date/time attached to it e.g., visit new theme park
 * Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
 * Events: tasks that start at a specific time and ends at a specific time.
 * At this point, dates/times can be treated as strings; there is no need to convert them to actual dates/times.
 * <p>
 */
public class Increment_06_Test extends TestStream {

    @Test
    public void Greet_AddToDo_Delete_List_Exit() throws Exception {

        assertNotNull(this.getPrintStream(), "printstream null?");

        Integer taskQty = 1;

        /*
         * Commands executed:
         *
         * add task 0 with task description
         * delete task 0
         * list
         * exit loop
         */

        StringBuilder commandBuilder = new StringBuilder();

        String taskDesc0 = "taskDesc abc";

        String store0Command= generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc0);
        String delete0Command = generateTextCommandDeleteTaskByTaskId(PROMPT_UNDER_TEST_DELETE_TASK, 0);
        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        commandBuilder.append(store0Command);
        commandBuilder.append(delete0Command);
        commandBuilder.append(listCommand);
        commandBuilder.append(exitCommand);
        System.setIn(new ByteArrayInputStream(commandBuilder.toString().getBytes()));

        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added todo" message
         * tabled tasks list
         * exit loop
         * terminate
         */
        StringBuilder expectedResponseBuilder = new StringBuilder();
        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());
        expectedResponseBuilder.append(getMsgUnderTestEntry());
        expectedResponseBuilder.append(getMsgUnderTestAttemptImport(frm.getImportPath()));
        expectedResponseBuilder.append(getMsgUnderTestReadPathNotFound());
        expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());
        expectedResponseBuilder.append(getMsgUnderTestResponseToDoAdded(taskDesc0));
        expectedResponseBuilder.append(getMsgUnderTestResponseTaskDeleted(0));

        MockTask[] MockTasks = {}; // since task is deleted

        expectedResponseBuilder.append(getMsgUnderTestResponseListAll(getPrettifyUnderTestList(MockTasks)));
        expectedResponseBuilder.append(getMsgUnderTestExitLoop());
        expectedResponseBuilder.append(getMsgUnderTestTerminate());

        String expectedOutputResponse = expectedResponseBuilder.toString();
             Main.run(this.getPrintStream(), tm,frm);
        assertEquals(expectedOutputResponse, this.getOutput());
    }


    @Test
    public void Greet_Add2ToDos_Delete_List_Exit() throws Exception {

        Integer taskQty = 2;

        /*
         * Commands executed:
         *
         * add task 0 with task description
         * add task 1 with task description
         * delete task 0
         * list
         * exit loop
         */

        StringBuilder commandBuilder = new StringBuilder();

        String taskDesc0 = "taskDesc abc";
        String taskDesc1 = "taskDesc def";

        String store0Command= generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc0);
        String store1Command= generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc1);
        String delete0Command = generateTextCommandDeleteTaskByTaskId(PROMPT_UNDER_TEST_DELETE_TASK, 0);
        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        commandBuilder.append(store0Command);
        commandBuilder.append(store1Command);
        commandBuilder.append(delete0Command);
        commandBuilder.append(listCommand);
        commandBuilder.append(exitCommand);
        System.setIn(new ByteArrayInputStream(commandBuilder.toString().getBytes()));

        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added todo" message
         * tabled tasks list
         * exit loop
         * terminate
         */
        StringBuilder expectedResponseBuilder = new StringBuilder();
        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());
        expectedResponseBuilder.append(getMsgUnderTestEntry());
        expectedResponseBuilder.append(getMsgUnderTestAttemptImport(frm.getImportPath()));
        expectedResponseBuilder.append(getMsgUnderTestReadPathNotFound());
        expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());
        expectedResponseBuilder.append(getMsgUnderTestResponseToDoAdded(taskDesc0));
        expectedResponseBuilder.append(getMsgUnderTestResponseToDoAdded(taskDesc1));
        expectedResponseBuilder.append(getMsgUnderTestResponseTaskDeleted(0));

        MockToDo expectedToDo1 = new MockToDo(taskDesc1, 1, false);

        MockTask[] MockTasks = {expectedToDo1}; // since task0 is deleted

        expectedResponseBuilder.append(getMsgUnderTestResponseListAll(getPrettifyUnderTestList(MockTasks)));
        expectedResponseBuilder.append(getMsgUnderTestExitLoop());
        expectedResponseBuilder.append(getMsgUnderTestTerminate());

        String expectedOutputResponse = expectedResponseBuilder.toString();
        Main.run(this.getPrintStream(), tm,frm);
        assertEquals(expectedOutputResponse, this.getOutput());
    }


}
