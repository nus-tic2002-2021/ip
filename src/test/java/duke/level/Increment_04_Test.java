package duke.level;

import duke.Main;
import duke.TaskManager;
import duke.mock.mockTask.MockEvent;
import duke.mock.mockTask.MockTask;
import duke.mock.mockTask.MockToDo;
import duke.testHelper.TestStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static duke.testHelper.help.OutputUnderTest.*;
import static duke.testHelper.help.PrettifyUnderTest.getPrettifyUnderTestList;
import static duke.testHelper.help.TextCommandUnderTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * ToDos: tasks without any date/time attached to it e.g., visit new theme park
 * Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
 * Events: tasks that start at a specific time and ends at a specific time.
 * At this point, dates/times can be treated as strings; there is no need to convert them to actual dates/times.
 * <p>
 */
public class Increment_04_Test extends TestStream {

    @Test
    public void TestLevel4_Greet_AddToDo_List_Exit() throws Exception {

        assertNotNull(this.getPrintStream(), "printstream null?");

        Integer taskQty = 1;

        /*
         * Commands executed:
         *
         * add task 0 with task description
         * list
         * exit loop
         */

        StringBuilder commandBuilder = new StringBuilder();

        String taskDesc0 = "taskDesc abc";

        String storeCommand0 = generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc0);
        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        commandBuilder.append(storeCommand0);
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

        MockToDo expectedToDo1 = new MockToDo(taskDesc0, 0, false);

        expectedResponseBuilder.append(getMsgUnderTestEntry());
        expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());
        expectedResponseBuilder.append(getMsgUnderTestResponseToDoAdded(taskDesc0));

        MockTask[] MockTasks = {expectedToDo1};

        expectedResponseBuilder.append(getMsgUnderTestResponseListAll(getPrettifyUnderTestList(MockTasks)));
        expectedResponseBuilder.append(getMsgUnderTestExitLoop());
        expectedResponseBuilder.append(getMsgUnderTestTerminate());

        String expectedOutputResponse = expectedResponseBuilder.toString();
        Main.run(this.getPrintStream(), new TaskManager());
        assertEquals(expectedOutputResponse, this.getOutput());
    }

    @Test
    public void TestLevel4_Greet_AddEvent_List_Exit() throws Exception {

        assertNotNull(this.getPrintStream(), "printstream null?");

        /*
         * Commands executed:
         *
         * add event 0 with task description
         * list
         * exit loop
         */

        StringBuilder commandBuilder = new StringBuilder();

        String taskDesc0 = "event_desc_abc asfasfasf";


        String fromDateString = "fromday";
        String toDateString = "today";
        String storeEventCommand0 = generateTextCommandLineAddEvent(PROMPT_UNDER_TEST_ADD_EVENT, taskDesc0,DELIMITER_EVENT_FROM,fromDateString,DELIMITER_EVENT_TO,toDateString);
        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        commandBuilder.append(storeEventCommand0);
        commandBuilder.append(listCommand);
        commandBuilder.append(exitCommand);
        System.setIn(new ByteArrayInputStream(commandBuilder.toString().getBytes()));

        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added event" message
         * tabled tasks list
         * exit loop
         * terminate
         */
        StringBuilder expectedResponseBuilder = new StringBuilder();

        MockEvent expectedEvent = new MockEvent(taskDesc0, 0, false, fromDateString,toDateString);

        expectedResponseBuilder.append(getMsgUnderTestEntry());
        expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());
        expectedResponseBuilder.append(getMsgUnderTestResponseEventAdded(taskDesc0));

        MockTask[] MockEvents = {expectedEvent};

        expectedResponseBuilder.append(getMsgUnderTestResponseListAll(getPrettifyUnderTestList(MockEvents)));
        expectedResponseBuilder.append(getMsgUnderTestExitLoop());
        expectedResponseBuilder.append(getMsgUnderTestTerminate());

        String expectedOutputResponse = expectedResponseBuilder.toString();
        Main.run(this.getPrintStream(), new TaskManager());
        assertEquals(expectedOutputResponse, this.getOutput());
    }



    @Test
    public void TestLevel4_UnknownCommand() throws Exception {

        String randomtextCommand = generateTextCommandRandom("s0meUnknownPrompt");
        String exitLoopCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);
        String commandLines = randomtextCommand + exitLoopCommand;
        System.setIn(new ByteArrayInputStream(commandLines.getBytes()));
        StringBuilder expectedResponseBuilder = new StringBuilder();

        expectedResponseBuilder.append(getMsgUnderTestEntry());
        expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());

        expectedResponseBuilder.append(getMsgUnderTestUnknownRequest());
        expectedResponseBuilder.append(getMsgUnderTestExitLoop());

        expectedResponseBuilder.append(getMsgUnderTestTerminate());

        String expectedOutputResponse = expectedResponseBuilder.toString();

        Main.run(this.getPrintStream(), new TaskManager());

        assertEquals(expectedOutputResponse, this.getOutput());

    }

}
