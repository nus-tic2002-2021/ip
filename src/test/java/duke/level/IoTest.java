package duke.level;

import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.mock.mockTask.MockDeadline;
import duke.mock.mockTask.MockEvent;
import duke.mock.mockTask.MockTask;
import duke.mock.mockTask.MockToDo;
import duke.testHelper.TestStream;
import duke.testHelper.help.CodeUnderTest.ParserUnderTest;
import org.junit.jupiter.api.Test;

import static duke.testHelper.help.Builder.*;
import static duke.testHelper.help.CodeUnderTest.OutputUnderTest.*;
import static duke.testHelper.help.CodeUnderTest.PrettifyUnderTest.getExpectedTaskList;
import static duke.testHelper.help.CodeUnderTest.TextCommandUnderTest.*;
import static duke.testHelper.help.config.dukeIOTestPath.getDefaultTasksImportTestPathString;
import static duke.testHelper.help.config.dukeIOTestPath.getDefaultTasksTestExportPathString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IoTest extends TestStream {

    @Test
    public void Greet_AddToDoList_Exit() throws Exception {

        // Arrange Input

        /*
         * Commands executed:
         *
         * add task 0 with task description
         * list
         * exit loop
         */


        String taskDesc0 = "taskDesc abc";

        String storeCommand0 = generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc0);
        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        System.setIn(buildCommandInputStream(storeCommand0, listCommand, exitCommand));

        // Arrange Expected Output
        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added todo" message
         * tabled tasks list
         * exit loop
         * terminate
         */

        MockToDo expectedToDo1 = new MockToDo(taskDesc0, 0, false);
        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());

        MockTask[] MockTasks = {expectedToDo1};
        String out0 = getExpectedOutputEntry();
        String out1 = getExpectedOutputImportAttempt(frm.getImportPath() );
        String out2 = getExpectedOutputReadPathNotFound();
        String out3 = getExpectedOutputBeginInputLoop();
        String out4 = getExpectedOutputAddedToDo(taskDesc0);
        String out5 = getExpectedOutputList(getExpectedTaskList(MockTasks));
        String out6 = getExpectedOutputExitInputLoop();
        String out7 = getExpectedOutputTerminate();
        String expectedOutputResponse = buildExpectedResponse(out0,out1, out2, out3, out4,out5,out6,out7);

        // Act
        Main.run(this.getPrintStream(), tm,frm);
        // Assert
        assertEquals(expectedOutputResponse, this.getOutput());
    }

    @Test
    public void Greet_AddEventList_Exit() throws Exception {

        /* Arrange Input
         * Commands executed:
         *
         * add event 0 with task description
         * list
         * exit loop
         */

        String taskDesc0 = "event_desc_abc asfasfasf";
        String fromDateString = "19990101";
        String toDateString = "19990202";

        String storeEventCommand0 = generateTextCommandLineAddEvent(PROMPT_UNDER_TEST_ADD_EVENT, taskDesc0,DELIMITER_EVENT_FROM,fromDateString,DELIMITER_EVENT_TO,toDateString);

        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        System.setIn(buildCommandInputStream(storeEventCommand0,listCommand,exitCommand));

        /*
         * Arrange Output
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added event" message
         * tabled tasks list
         * exit loop
         * terminate
         */

        MockEvent expectedEvent = new MockEvent(taskDesc0, 0, false, ParserUnderTest.parseStringAsLocalDateTime(fromDateString),ParserUnderTest.parseStringAsLocalDateTime(toDateString));
        MockTask[] MockEvents = {expectedEvent};

        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());

        String out1 = getExpectedOutputEntry();
        String out2 = getExpectedOutputImportAttempt(frm.getImportPath());
        String out3 = getExpectedOutputReadPathNotFound();
        String out4 = getExpectedOutputBeginInputLoop();
        String out5 = getExpectedOutputAddedEvent(expectedEvent.getDesc());
        String out6 = getExpectedOutputList(getExpectedTaskList(MockEvents));
        String out7 = getExpectedOutputExitInputLoop();
        String out8 = getExpectedOutputTerminate();

        String expectedOutputResponse = buildString(out1,out2,out3,out4,out5,out6,out7,out8);

        Main.run(this.getPrintStream(), tm,frm);
        assertEquals(expectedOutputResponse, this.getOutput());
    }

    @Test
    public void Greet_AddDeadline_List_Exit() throws Exception {

        /* Arrange Input
         * Commands executed:
         *
         * add deadline 0 with task description
         * list
         * exit loop
         */


        String taskDesc0 = "event_desc_abc asfasfasf";
        String byDateString = "20200101";

        String storeDeadlineCommand0 = generateTextCommandLineAddDeadline(PROMPT_UNDER_TEST_ADD_DEADLINE, taskDesc0,DELIMITER_DEADLINE_DEADLINE,byDateString);
        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);
        System.setIn(buildCommandInputStream(storeDeadlineCommand0,listCommand,exitCommand));

        /* Arrange Expected Output
         *
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added event" message
         * tabled tasks list
         * exit loop
         * terminate
         */

        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());

        MockDeadline expectedEvent = new MockDeadline(taskDesc0, 0, false, ParserUnderTest.parseStringAsLocalDateTime(byDateString));
        MockTask[] MockDeadlines = {expectedEvent};

        String out0 = getExpectedOutputEntry();
        String out1 = getExpectedOutputImportAttempt(frm.getImportPath());
        String out2 = getExpectedOutputReadPathNotFound();
        String out3 = getExpectedOutputBeginInputLoop();
        String out4 = getExpectedOutputAddedDeadline(taskDesc0);
        String out5 = getExpectedOutputList(getExpectedTaskList(MockDeadlines));
        String out6 = getExpectedOutputExitInputLoop();
        String out7 = getExpectedOutputTerminate();

        String expectedOutputResponse = buildExpectedResponse(out0,out1,out2,out3,out4,out5,out6,out7);
        Main.run(this.getPrintStream(), tm,frm);
        assertEquals(expectedOutputResponse, this.getOutput());
    }

    @Test
    public void GreetAddEachTaskType_List() throws Exception{
        // Arrange Input

        String task0ToDoDescription = "todo_desc asfasfasf";
        String task1DeadlineDescription = "deadline_desc ndfrgndfndfn";
        String task1DeadlineByString = "20010101";
        String task2EventDescription = "event_desc_abc 213t12 3b52";
        String task2EventFromDateString = "20200102";
        String task2EventToDateString = "20200102";

        String storeToDoCommand0 = generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, task0ToDoDescription);
        String storeDeadlineCommand1 = generateTextCommandLineAddDeadline(PROMPT_UNDER_TEST_ADD_DEADLINE, task1DeadlineDescription,DELIMITER_DEADLINE_DEADLINE,task1DeadlineByString);
        String storeEventCommand2 = generateTextCommandLineAddEvent(PROMPT_UNDER_TEST_ADD_EVENT, task2EventDescription,DELIMITER_EVENT_FROM,task2EventFromDateString,DELIMITER_EVENT_TO,task2EventToDateString);

        String listCommand = generateTextCommandList(PROMPT_UNDER_TEST_LIST);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        System.setIn(buildCommandInputStream(new String[]{storeToDoCommand0,storeDeadlineCommand1,storeEventCommand2,listCommand,exitCommand}));


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



        MockToDo expectedToDo = new MockToDo(task0ToDoDescription, 0,false);

        MockDeadline expectedDeadline = new MockDeadline(task1DeadlineDescription, 1, false, ParserUnderTest.parseStringAsLocalDateTime(task1DeadlineByString));
        MockEvent expectedEvent = new MockEvent(task2EventDescription, 2, false, ParserUnderTest.parseStringAsLocalDateTime(task2EventFromDateString),ParserUnderTest.parseStringAsLocalDateTime(task2EventToDateString));

        MockTask[] MockEvents = {expectedToDo,expectedDeadline,expectedEvent};

        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());

        String out0 = getExpectedOutputEntry();
        String out1 = getExpectedOutputImportAttempt(frm.getImportPath());
        String out2 = getExpectedOutputReadPathNotFound();
        String out3 = getExpectedOutputBeginInputLoop();
        String out4 = getExpectedOutputAddedToDo(task0ToDoDescription);
        String out5 = getExpectedOutputAddedDeadline(task1DeadlineDescription);
        String out6 = getExpectedOutputAddedEvent(task2EventDescription);
        String out7 = getExpectedOutputList(getExpectedTaskList(MockEvents));
        String out8 = getExpectedOutputExitInputLoop();
        String out9 = getExpectedOutputTerminate();

        String expectedOutputResponse = buildExpectedResponse(out0,out1,out2,out3,out4,out5,out6,out7,out8,out9);
        Main.run(this.getPrintStream(), tm,frm);

        assertEquals(expectedOutputResponse, this.getOutput());

    }

    @Test
    public void TestUnknownCommand() throws Exception {

        String randomTextCommand = generateTextCommandRandom("s0meUnknownPrompt");
        String exitLoopCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);
        System.setIn(buildCommandInputStream(randomTextCommand,exitLoopCommand));

        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());
        String out0 = getExpectedOutputEntry();
        String out1 = getExpectedOutputImportAttempt(frm.getImportPath());
        String out2 = getExpectedOutputReadPathNotFound();
        String out3 = getExpectedOutputBeginInputLoop();
        String out4 = getExpectedOutputCommandUnknown();
        String out5 = getExpectedOutputExitInputLoop();
        String out6 = getExpectedOutputTerminate();

        String expectedOutputResponse = buildExpectedResponse(out0,out1,out2,out3,out4,out5,out6);

        Main.run(this.getPrintStream(), tm,frm);

        assertEquals(expectedOutputResponse, this.getOutput());
    }

}
