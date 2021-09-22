


package duke.level;

import duke.Main;
import duke.TaskManager;
import duke.testHelper.TestStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static duke.testHelper.help.OutputUnderTest.*;
import static duke.testHelper.help.PrettifyUnderTest.getPrettifyUnderTestList;
import static duke.testHelper.help.TextCommandUnderTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Add the ability to mark tasks as done.
 * <p>
 *
 * @see <a href="https://nus-tic2002-2021.github.io/website/se-book-adapted/projectDuke/index.html#level-3-mark-as-done">Level-3</a>
 */
public class Increment_03_Test extends TestStream {

    @Test
    public void TestLevel3_Greet_AddTask_MarkTaskAsDone_Exit() throws Exception {
        assertNotNull(this.getPrintStream(),"printstream null?");

        Integer taskQty = 2;

        /*
         * Commands executed:
         *
         * add task 0 with task description
         * add task 1 with task description
         * mark task 0 as done
         * list
         * exit loop
         */
        StringBuilder commandBuilder = new StringBuilder();

        String storeText0 = "taskDesc abc";
        String storeText1 = "todoDesc def";

        String storeCommand0 = generateTextCommandFullTextAsNewToDoDescription(storeText0);
        String storeCommand1 = generateTextCommandFullTextAsNewToDoDescription(storeText1);
        String mark0AsDoneCommand = generateTextCommandSetCompleted(PROMPT_UNDER_TEST_MARK_AS_DONE, 0);

        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        commandBuilder.append(storeCommand0);
        commandBuilder.append(storeCommand1);
        commandBuilder.append(mark0AsDoneCommand);
        commandBuilder.append(listCommand);
        commandBuilder.append(exitCommand);
        System.setIn(new ByteArrayInputStream(commandBuilder.toString().getBytes()));


        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added string" message
         * "Added string" message
         * "Set as done" message
         * tabled tasks list
         * exit loop
         * terminate
         */
        StringBuilder expectedResponseBuilder = new StringBuilder();



        Integer expectedTask00Id = 0;
        Boolean expectedTask00DoneStatus = true;
        String expectedTask00TaskType = " ";
        String expectedTask00Description = storeText0;
        String expectedTask00Chronology = " ";

        Integer expectedTask01Id = 1;
        Boolean expectedTask01DoneStatus = false;
        String expectedTask01TaskType = " ";
        String expectedTask01Description = storeText1;
        String expectedTask01Chronology = " ";


        expectedResponseBuilder.append(getMsgUnderTestEntry());
        expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());
        expectedResponseBuilder.append(getMsgUnderTestResponseTaskAdded(expectedTask00Description));
        expectedResponseBuilder.append(getMsgUnderTestResponseTaskAdded(expectedTask01Description));
        expectedResponseBuilder.append(getMsgUnderTestResponseTaskSetCompleted(expectedTask00Id));


        List<Integer> taskIdList = List.of(expectedTask00Id, expectedTask01Id);
        List<Boolean> taskDoneStatusList = List.of(expectedTask00DoneStatus, expectedTask01DoneStatus);
        List<String> taskTypeList = List.of(expectedTask00TaskType, expectedTask01TaskType);
        List<String> taskDoneDescription = List.of(expectedTask00Description, expectedTask01Description);
        List<String> taskChronologyList = List.of(expectedTask00Chronology, expectedTask01Chronology);


        expectedResponseBuilder.append(getMsgUnderTestResponseListAll(getPrettifyUnderTestList(taskQty, taskIdList, taskDoneStatusList, taskTypeList, taskDoneDescription, taskChronologyList)));
        expectedResponseBuilder.append(getMsgUnderTestExitLoop());
        expectedResponseBuilder.append(getMsgUnderTestTerminate());

        String expectedOutputResponse = expectedResponseBuilder.toString();
        Main.run(this.getPrintStream(),new TaskManager());
        assertEquals(expectedOutputResponse, this.getOutput());
    }

}
