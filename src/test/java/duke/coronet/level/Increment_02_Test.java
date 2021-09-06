


package duke.coronet.level;

import duke.coronet.manager.TaskManager;
import duke.coronet.manager.UxManager;
import duke.coronet.orchestra.OrchestratorLevel02;
import duke.coronet.testHelper.TestStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static duke.coronet.testHelper.help.OutputUnderTest.*;
import static duke.coronet.testHelper.help.PrettifyUnderTest.getPrettifyUnderTestList;
import static duke.coronet.testHelper.help.TextCommandUnderTest.*;
import static org.junit.Assert.assertEquals;

/**
 * Add the ability to store whatever text entered by the user and display them back to the user when requested.
 *
 * Uses OrchestratorLevel02
 *
 * @see <a href="https://nus-tic2002-2021.github.io/website/se-book-adapted/projectDuke/index.html#level-2-add-list">Level-2</a>
 */
public class Increment_02_Test extends TestStream {


    /**
     * In-memory storage of 2 text commands as to\Do descriptions and lists them in ascending id order
     */
    @Test
    public void TestLevel2GreetEchoExit_Using2ToDos() throws Exception {

        Integer taskQty = 2;

        /*
         * Commands executed:
         *
         * someTodoDesc1
         * someTodoDesc2
         * list
         * exit loop
         */
        StringBuilder commandBuilder = new StringBuilder();

        String storeText1 = "taskDesc abc";
        String storeText2 = "todoDesc def";

        String storeCommand1 = generateTextCommandFullTextAsNewToDoDescription(storeText1);
        String storeCommand2 = generateTextCommandFullTextAsNewToDoDescription(storeText2);
        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        commandBuilder.append(storeCommand1);
        commandBuilder.append(storeCommand2);
        commandBuilder.append(listCommand);
        commandBuilder.append(exitCommand);
        System.setIn(new ByteArrayInputStream(commandBuilder.toString().getBytes()));


        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added toDo1" message
         * "Added toDo2" message
         * tabled tasks list
         * exit loop
         * terminate
         */
        StringBuilder expectedResponseBuilder = new StringBuilder();

        Integer expectedTask01Id = 0;
        Boolean expectedTask01DoneStatus = false;
        String expectedTask01TaskType = "T";
        String expectedTask01Description = storeText1;
        String expectedTask01Chronology = "-";

        Integer expectedTask02Id = 1;
        Boolean expectedTask02DoneStatus = false;
        String expectedTask02TaskType = "T";
        String expectedTask02Description = storeText2;
        String expectedTask02Chronology = "-";

        List<Integer> taskIdList = List.of(expectedTask01Id, expectedTask02Id);
        List<Boolean> taskDoneStatusList = List.of(expectedTask01DoneStatus, expectedTask02DoneStatus);
        List<String> taskTypeList = List.of(expectedTask01TaskType, expectedTask02TaskType);
        List<String> taskDoneDescription = List.of(expectedTask01Description, expectedTask02Description);
        List<String> taskChronologyList = List.of(expectedTask01Chronology, expectedTask02Chronology);

        expectedResponseBuilder.append(getMsgUnderTestEntry());
        expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());
        expectedResponseBuilder.append(getMsgUnderTestResponseTaskAdded(expectedTask01Description));
        expectedResponseBuilder.append(getMsgUnderTestResponseTaskAdded(expectedTask02Description));
        expectedResponseBuilder.append(getMsgUnderTestResponseListAll(getPrettifyUnderTestList(taskQty, taskIdList, taskDoneStatusList, taskTypeList, taskDoneDescription, taskChronologyList)));
        expectedResponseBuilder.append(getMsgUnderTestExitLoop());
        expectedResponseBuilder.append(getMsgUnderTestTerminate());

        String expectedOutputResponse = expectedResponseBuilder.toString();
        new OrchestratorLevel02(new TaskManager(), null, new UxManager(this.getPrintStream())).run();
        assertEquals(expectedOutputResponse, this.getOutput());
    }

}
