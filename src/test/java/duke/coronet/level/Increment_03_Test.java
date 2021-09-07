


package duke.coronet.level;

import duke.coronet.manager.TaskManager;
import duke.coronet.manager.UxManager;
import duke.coronet.orchestra.OrchestratorLevel03;
import duke.coronet.testHelper.TestStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static duke.coronet.testHelper.help.OutputUnderTest.*;
import static duke.coronet.testHelper.help.PrettifyUnderTest.getPrettifyUnderTestList;
import static duke.coronet.testHelper.help.TextCommandUnderTest.*;
import static org.junit.Assert.assertEquals;

/**
 * Add the ability to mark tasks as done.
 * <p>
 * Uses OrchestratorLevel03
 *
 * @see <a href="https://nus-tic2002-2021.github.io/website/se-book-adapted/projectDuke/index.html#level-3-mark-as-done">Level-3</a>
 */
public class Increment_03_Test extends TestStream {


    @Test
    public void TestLevel3MarkAsDone_TaskTypeToDo() throws Exception {

        Integer taskQty = 1;

        /*
         * textCommands executed:
         *
         * someTaskDescription
         * list
         * done [id]
         * list
         * exit loop
         */
        StringBuilder commandBuilder = new StringBuilder();

        String storeText1 = "taskDesc abc";

        String storeCommand1 = generateTextCommandFullTextAsNewToDoDescription(storeText1);
        String markAsDoneCommand = generateTextCommandSetCompleted(PROMPT_UNDER_TEST_MARK_AS_DONE, 0);
        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitLoopCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        commandBuilder.append(storeCommand1);
        commandBuilder.append(listCommand);
        /* Mark Task As Done */
        commandBuilder.append(markAsDoneCommand);
        commandBuilder.append(listCommand);
        commandBuilder.append(exitLoopCommand);
        System.setIn(new ByteArrayInputStream(commandBuilder.toString().getBytes()));
        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added toDo1" message
         * tabled tasks list
         * "set done"
         * tabled tasks list
         * exit loop
         * terminate
         */
        StringBuilder expectedResponseBuilder = new StringBuilder();

        Integer expectedTask01Id = 0;
        Boolean expectedTask01DoneStatusDefault = false;
        Boolean expectedTask01DoneStatusUpdated = true;
        String expectedTask01TaskType = "T";
        String expectedTask01Description = storeText1;
        String expectedTask01Chronology = "-";


        List<Integer> taskIdList = List.of(expectedTask01Id);
        List<Boolean> taskDoneStatusList = List.of(expectedTask01DoneStatusDefault);
        List<String> taskTypeList = List.of(expectedTask01TaskType);
        List<String> taskDoneDescription = List.of(expectedTask01Description);
        List<String> taskChronologyList = List.of(expectedTask01Chronology);


        List<Boolean> newTaskDoneStatusList = List.of(expectedTask01DoneStatusUpdated);

        expectedResponseBuilder.append(getMsgUnderTestEntry());
        expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());
        expectedResponseBuilder.append(getMsgUnderTestResponseTaskAdded(expectedTask01Description));
        expectedResponseBuilder.append(getMsgUnderTestResponseListAll(getPrettifyUnderTestList(taskQty, taskIdList, taskDoneStatusList, taskTypeList, taskDoneDescription, taskChronologyList)));
        expectedResponseBuilder.append(getMsgUnderTestResponseTaskSetCompleted(expectedTask01Id));

        /* ExpectedToggle */
        expectedResponseBuilder.append(getMsgUnderTestResponseListAll(getPrettifyUnderTestList(taskQty, taskIdList, newTaskDoneStatusList, taskTypeList, taskDoneDescription, taskChronologyList)));
        expectedResponseBuilder.append(getMsgUnderTestExitLoop());
        expectedResponseBuilder.append(getMsgUnderTestTerminate());

        String expectedOutputResponse = expectedResponseBuilder.toString();
        new OrchestratorLevel03(new TaskManager(), null, new UxManager(this.getPrintStream())).run();
        assertEquals(expectedOutputResponse, this.getOutput());
    }
}
