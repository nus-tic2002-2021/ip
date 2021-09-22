package duke.level;

import duke.Main;
import duke.TaskManager;
import duke.testHelper.TestStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static duke.testHelper.help.PrettifyUnderTest.getPrettifyUnderTestList;
import static duke.testHelper.help.TextCommandUnderTest.*;
import static duke.testHelper.help.OutputUnderTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Implement a skeletal version of Duke that starts by greeting the user,
 * simply echos commands entered by the user, and exits when the user types bye.
 * <p>
 *
 * @see <a href="https://nus-tic2002-2021.github.io/website/se-book-adapted/projectDuke/index.html#level-1-greet-echo-exit">Level-1</a>
 */
public class Increment_02_Test extends TestStream {

        /**
         * In-memory storage of 2 text commands as (To Do) descriptions and lists them
         */
        @Test
        public void TestLevel2GreetEchoExit() throws Exception {

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
             * "Added string" message
             * "Added string" message
             * tabled tasks list
             * exit loop
             * terminate
             */
            StringBuilder expectedResponseBuilder = new StringBuilder();

            Integer expectedTask01Id = 0;
            Boolean expectedTask01DoneStatus = false;
            String expectedTask01TaskType = " ";
            String expectedTask01Description = storeText1;
            String expectedTask01Chronology = " ";

            Integer expectedTask02Id = 0;
            Boolean expectedTask02DoneStatus = false;
            String expectedTask02TaskType = " ";
            String expectedTask02Description = storeText2;
            String expectedTask02Chronology = " ";



            expectedResponseBuilder.append(getMsgUnderTestEntry());
            expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());
            expectedResponseBuilder.append(getMsgUnderTestResponseTaskAdded(expectedTask01Description));
            expectedResponseBuilder.append(getMsgUnderTestResponseTaskAdded(expectedTask02Description));


            List<Integer> taskIdList = List.of(expectedTask01Id, expectedTask02Id);
            List<Boolean> taskDoneStatusList = List.of(expectedTask01DoneStatus, expectedTask02DoneStatus);
            List<String> taskTypeList = List.of(expectedTask01TaskType, expectedTask02TaskType);
            List<String> taskDoneDescription = List.of(expectedTask01Description, expectedTask02Description);
            List<String> taskChronologyList = List.of(expectedTask01Chronology, expectedTask02Chronology);

            expectedResponseBuilder.append(getMsgUnderTestResponseListAll(getPrettifyUnderTestList(taskQty, taskIdList, taskDoneStatusList, taskTypeList, taskDoneDescription, taskChronologyList)));
            expectedResponseBuilder.append(getMsgUnderTestExitLoop());
            expectedResponseBuilder.append(getMsgUnderTestTerminate());

            String expectedOutputResponse = expectedResponseBuilder.toString();
            Main.run(this.getPrintStream(),new TaskManager());
            assertEquals(expectedOutputResponse, this.getOutput());
    }
}