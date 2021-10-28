package duke.unittest;

import static duke.testhelper.help.Builder.buildCommandInputStream;
import static duke.testhelper.help.Builder.buildExpectedResponse;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputBeginInputLoop;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputExitInputLoop;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputListTasksWithinPeriod;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputStatsAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import duke.TaskManager;
import duke.Ui;
import duke.mock.mocktask.MockDeadline;
import duke.mock.mocktask.MockEvent;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.ToDo;
import duke.testhelper.TestStream;
import duke.testhelper.help.codeundertest.PrettifyUnderTest;
import duke.testhelper.help.codeundertest.TextCommandUnderTest;

public class UiTest extends TestStream {

    @Test
    public void project_withinNextDays_filteredAndSorted() throws Exception {
        int period = 30;
        // create some tasks in taskmanager, filter range in next 30 days

        Ui ui = new Ui(this.getPrintStream());
        TaskManager tm = new TaskManager();

        LocalDate today = LocalDate.now();
        // Add tasks to task manager and mock expected tasks

        // task 0
        String toDoDesc = "todo should not appear";
        tm.addNewToDo(toDoDesc);

        // task 1
        String deadlineDesc = "due in " + period + " days";
        LocalDateTime dldl = LocalDateTime.now().plusDays(period);
        tm.addNewDeadline(deadlineDesc, dldl);
        MockDeadline task1 = new MockDeadline(deadlineDesc, 1, false, dldl);

        // task 2
        tm.addNewDeadline("assignment due beyond period", LocalDateTime.now().plusDays(period + 200));

        // task 3
        String eventDesc = "event start today";
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusDays(period + 1);
        tm.addNewEvent(eventDesc, from, to);
        MockEvent task3 = new MockEvent(eventDesc, 3, false, from, to);

        // build commands
        String out0 = getExpectedOutputBeginInputLoop();
        String in0 = TextCommandUnderTest.generateTextCommandProjection(period);

        String expectedList = PrettifyUnderTest.getExpectedTaskList(task3, task1);
        String out1 = getExpectedOutputListTasksWithinPeriod(expectedList, period);

        String in1 = TextCommandUnderTest.generateTextCommandExit();
        String out2 = getExpectedOutputExitInputLoop();

        System.setIn(buildCommandInputStream(in0, in1));
        ui.textCommandLoop(tm, null);

        String expectedOutput = buildExpectedResponse(out0, out1, out2);
        assertEquals(expectedOutput, this.getOutput());
    }

    /**
     * create a collection of tasks, summarise by task type and completion status.
     */
    @Test
    public void stats_showSummaryAll() throws Exception {

        TaskManager tm = new TaskManager();
        Integer expectedCountToDo = 0;
        Integer expectedCountDeadline = 0;
        Integer expectedCountEvent = 0;
        LocalDateTime date = LocalDateTime.now();
        ToDo task0 = tm.addNewToDo("todo done" + expectedCountToDo++);
        task0.setDoneStatus(true);
        Deadline task1 = tm.addNewDeadline("deadline" + expectedCountDeadline++, date);
        Event task2 = tm.addNewEvent("event" + expectedCountEvent++, date, date);

        String out0 = getExpectedOutputBeginInputLoop();
        String in0 = TextCommandUnderTest.generateTextCommandStatsAll();
        String stats = PrettifyUnderTest.getExpectedStatisticsAll(tm.getAllAsArray());
        String out1 = getExpectedOutputStatsAll(stats);
        String in1 = TextCommandUnderTest.generateTextCommandExit();
        String out2 = getExpectedOutputExitInputLoop();
        System.setIn(buildCommandInputStream(in0, in1));
        String expectedOutput = buildExpectedResponse(out0, out1, out2);
        new Ui(this.getPrintStream()).textCommandLoop(tm, null);
        assertEquals(expectedOutput, this.getOutput());
    }
}
