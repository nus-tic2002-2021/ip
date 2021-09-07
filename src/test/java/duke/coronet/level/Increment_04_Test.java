


package duke.coronet.level;

import duke.coronet.manager.TaskManager;
import duke.coronet.manager.UxManager;
import duke.coronet.orchestra.OrchestratorLevel04;
import duke.coronet.testHelper.TestStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static duke.coronet.testHelper.help.OutputUnderTest.*;
import static duke.coronet.testHelper.help.PrettifyUnderTest.getPrettifyUnderTestList;
import static duke.coronet.testHelper.help.TextCommandUnderTest.*;
import static duke.coronet.testHelper.help.TextCommandUnderTest.PROMPT_UNDER_TEST_ADD_TO_DO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * ToDos: tasks without any date/time attached to it e.g., visit new theme park
 * Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
 * Events: tasks that start at a specific time and ends at a specific time.
 * <p>
 * Uses OrchestratorLevel04
 *
 * @see <a href="https://nus-tic2002-2021.github.io/website/se-book-adapted/projectDuke/index.html#level-4-todos-events-deadlines">Level-3</a>
 */
public class Increment_04_Test extends TestStream {


    @Test
    public void TestLevel4_AddToDo() throws Exception {

        Integer taskQty = 1;

        /*
         * textCommands executed:
         *
         * someTaskDescription
         * list
         * exit loop
         */
        StringBuilder commandBuilder = new StringBuilder();

        String taskDescription = "first task description";
        String storeToDoCommand = generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO,taskDescription);
        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        commandBuilder.append(storeToDoCommand);
        commandBuilder.append(listCommand);
        commandBuilder.append(exitCommand);
        System.setIn(new ByteArrayInputStream(commandBuilder.toString().getBytes()));
        /*
         * Should display:
         * Entry Message
         * user input loop message
         * added todo message
         * tabled tasks list
         * exit loop
         * terminate
         */
        StringBuilder expectedResponseBuilder = new StringBuilder();

        Integer expectedTask01Id = 0;
        Boolean expectedTask01DoneStatus = false;
        String expectedTask01TaskType = "T";
        String expectedTask01Description = taskDescription;
        String expectedTask01Chronology = "-";


        List<Integer> taskIdList = List.of(expectedTask01Id);
        List<Boolean> taskDoneStatusList = List.of(expectedTask01DoneStatus);
        List<String> taskTypeList = List.of(expectedTask01TaskType);
        List<String> taskDoneDescription = List.of(expectedTask01Description);
        List<String> taskChronologyList = List.of(expectedTask01Chronology);



        expectedResponseBuilder.append(getMsgUnderTestEntry());
        expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());

        expectedResponseBuilder.append(getMsgUnderTestResponseTaskAdded(expectedTask01Description));
        expectedResponseBuilder.append(getMsgUnderTestResponseListAll(getPrettifyUnderTestList(taskQty, taskIdList, taskDoneStatusList, taskTypeList, taskDoneDescription, taskChronologyList)));
        expectedResponseBuilder.append(getMsgUnderTestExitLoop());

        expectedResponseBuilder.append(getMsgUnderTestTerminate());

        String expectedOutputResponse = expectedResponseBuilder.toString();
        new OrchestratorLevel04(new TaskManager(), null, new UxManager(this.getPrintStream())).run();
        assertEquals(expectedOutputResponse, this.getOutput());
    }

    @Test
    public void TestLevel4_AddDeadline() throws Exception {
        fail();
    }

    @Test
    public void TestLevel4_AddEvent() throws Exception {
        fail();
    }


    @Test
    public void TestLevel4_UnknownRequest() throws Exception {
        fail();
    }

}
