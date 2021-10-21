package duke.level;

import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.mock.mockTask.MockDeadline;
import duke.mock.mockTask.MockEvent;
import duke.mock.mockTask.MockTask;
import duke.mock.mockTask.MockToDo;
import duke.testHelper.TestStream;
import duke.testHelper.help.ParserUnderTest;
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
        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());
        expectedResponseBuilder.append(getMsgUnderTestEntry());
        expectedResponseBuilder.append(getMsgUnderTestAttemptImport(frm.getImportPath()));
        expectedResponseBuilder.append(getMsgUnderTestReadPathNotFound());
        expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());
        expectedResponseBuilder.append(getMsgUnderTestResponseToDoAdded(taskDesc0));

        MockTask[] MockTasks = {expectedToDo1};

        expectedResponseBuilder.append(getMsgUnderTestResponseListAll(getPrettifyUnderTestList(MockTasks)));
        expectedResponseBuilder.append(getMsgUnderTestExitLoop());
        expectedResponseBuilder.append(getMsgUnderTestTerminate());

        String expectedOutputResponse = expectedResponseBuilder.toString();
        Main.run(this.getPrintStream(), tm,frm);

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


        String fromDateString = "19990101";
        String toDateString = "19990202";
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
        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());
        MockEvent expectedEvent = new MockEvent(taskDesc0, 0, false, ParserUnderTest.parseStringAsLocalDateTime(fromDateString),ParserUnderTest.parseStringAsLocalDateTime(toDateString));

        expectedResponseBuilder.append(getMsgUnderTestEntry());
        expectedResponseBuilder.append(getMsgUnderTestAttemptImport(frm.getImportPath()));
        expectedResponseBuilder.append(getMsgUnderTestReadPathNotFound());
        expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());
        expectedResponseBuilder.append(getMsgUnderTestResponseEventAdded(expectedEvent.getDesc()));

        MockTask[] MockEvents = {expectedEvent};

        expectedResponseBuilder.append(getMsgUnderTestResponseListAll(getPrettifyUnderTestList(MockEvents)));
        expectedResponseBuilder.append(getMsgUnderTestExitLoop());
        expectedResponseBuilder.append(getMsgUnderTestTerminate());

        String expectedOutputResponse = expectedResponseBuilder.toString();
        Main.run(this.getPrintStream(), tm,frm);
        assertEquals(expectedOutputResponse, this.getOutput());
    }

    @Test
    public void TestLevel4_Greet_AddDeadline_List_Exit() throws Exception {

        assertNotNull(this.getPrintStream(), "printstream null?");

        /*
         * Commands executed:
         *
         * add deadline 0 with task description
         * list
         * exit loop
         */

        StringBuilder commandBuilder = new StringBuilder();

        String taskDesc0 = "event_desc_abc asfasfasf";


        String byDateString = "midnight";
        String storeDeadlineCommand0 = generateTextCommandLineAddDeadline(PROMPT_UNDER_TEST_ADD_DEADLINE, taskDesc0,DELIMITER_DEADLINE_DEADLINE,byDateString);
        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        commandBuilder.append(storeDeadlineCommand0);
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

        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());


        MockDeadline expectedEvent = new MockDeadline(taskDesc0, 0, false, byDateString);

        expectedResponseBuilder.append(getMsgUnderTestEntry());
        expectedResponseBuilder.append(getMsgUnderTestAttemptImport(frm.getImportPath()));
        expectedResponseBuilder.append(getMsgUnderTestReadPathNotFound());
        expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());
        expectedResponseBuilder.append(getMsgUnderTestResponseDeadlineAdded(taskDesc0));

        MockTask[] MockDeadlines = {expectedEvent};

        expectedResponseBuilder.append(getMsgUnderTestResponseListAll(getPrettifyUnderTestList(MockDeadlines)));
        expectedResponseBuilder.append(getMsgUnderTestExitLoop());
        expectedResponseBuilder.append(getMsgUnderTestTerminate());

        String expectedOutputResponse = expectedResponseBuilder.toString();
        Main.run(this.getPrintStream(), tm,frm);
        assertEquals(expectedOutputResponse, this.getOutput());
    }




    @Test
    public void TestLevel4_UnknownCommand() throws Exception {

        String randomtextCommand = generateTextCommandRandom("s0meUnknownPrompt");
        String exitLoopCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);
        String commandLines = randomtextCommand + exitLoopCommand;
        System.setIn(new ByteArrayInputStream(commandLines.getBytes()));
        StringBuilder expectedResponseBuilder = new StringBuilder();

        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());
        expectedResponseBuilder.append(getMsgUnderTestEntry());

        expectedResponseBuilder.append(getMsgUnderTestAttemptImport(frm.getImportPath()));
        expectedResponseBuilder.append(getMsgUnderTestReadPathNotFound());

        expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());

        expectedResponseBuilder.append(getMsgUnderTestUnknownRequest());
        expectedResponseBuilder.append(getMsgUnderTestExitLoop());

        expectedResponseBuilder.append(getMsgUnderTestTerminate());

        String expectedOutputResponse = expectedResponseBuilder.toString();

        Main.run(this.getPrintStream(), tm,frm);

        assertEquals(expectedOutputResponse, this.getOutput());

    }

    @Test
    public void TestLevel4_Greet_AddEachEvent_List_Exit() throws Exception{
        String task0ToDoDescription = "todo_desc asfasfasf";
        String task1DeadlineDescription = "deadline_desc ndfrgndfndfn";
        String task1DeadlineByString = "someDeadline";
        String task2EventDescription = "event_desc_abc 213t12 3b52";
        String task2EventFromDateString = "20200102";
        String task2EventToDateString = "20200102";

        String storeToDoCommand0 = generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, task0ToDoDescription);
        String storeDeadlineCommand1 = generateTextCommandLineAddDeadline(PROMPT_UNDER_TEST_ADD_DEADLINE, task1DeadlineDescription,DELIMITER_DEADLINE_DEADLINE,task1DeadlineByString);
        String storeEventCommand2 = generateTextCommandLineAddEvent(PROMPT_UNDER_TEST_ADD_EVENT, task2EventDescription,DELIMITER_EVENT_FROM,task2EventFromDateString,DELIMITER_EVENT_TO,task2EventToDateString);

        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);


        StringBuilder commandBuilder = new StringBuilder();

        commandBuilder.append(storeToDoCommand0);
        commandBuilder.append(storeDeadlineCommand1);
        commandBuilder.append(storeEventCommand2);
        commandBuilder.append(listCommand);
        commandBuilder.append(exitCommand);
        System.setIn(new ByteArrayInputStream(commandBuilder.toString().getBytes()));


        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added todo" message
         * "Added deadline" message
         * "Added event" message
         * tabled tasks list
         * exit loop
         * terminate
         */


        StringBuilder expectedResponseBuilder = new StringBuilder();

        MockToDo expectedToDo = new MockToDo(task0ToDoDescription, 0,false);
        MockDeadline expectedDeadline = new MockDeadline(task1DeadlineDescription, 1, false, task1DeadlineByString);
        MockEvent expectedEvent = new MockEvent(task2EventDescription, 2, false, ParserUnderTest.parseStringAsLocalDateTime(task2EventFromDateString),ParserUnderTest.parseStringAsLocalDateTime(task2EventToDateString));

        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());

        expectedResponseBuilder.append(getMsgUnderTestEntry());
        expectedResponseBuilder.append(getMsgUnderTestAttemptImport(frm.getImportPath()));
        expectedResponseBuilder.append(getMsgUnderTestReadPathNotFound());
        expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());
        expectedResponseBuilder.append(getMsgUnderTestResponseToDoAdded(task0ToDoDescription));
        expectedResponseBuilder.append(getMsgUnderTestResponseDeadlineAdded(task1DeadlineDescription));
        expectedResponseBuilder.append(getMsgUnderTestResponseEventAdded(task2EventDescription));

        MockTask[] MockEvents = {expectedToDo,expectedDeadline,expectedEvent};

        expectedResponseBuilder.append(getMsgUnderTestResponseListAll(getPrettifyUnderTestList(MockEvents)));
        expectedResponseBuilder.append(getMsgUnderTestExitLoop());
        expectedResponseBuilder.append(getMsgUnderTestTerminate());

        String expectedOutputResponse = expectedResponseBuilder.toString();
        Main.run(this.getPrintStream(), tm,frm);

        assertEquals(expectedOutputResponse, this.getOutput());

    }

}
