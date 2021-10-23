package duke;

import static duke.testhelper.help.Builder.buildCommandInputStream;
import static duke.testhelper.help.Builder.buildExpectedResponse;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputBeginInputLoop;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputExitInputLoop;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputListTasksWithinPeriod;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import duke.mock.mocktask.MockDeadline;
import duke.mock.mocktask.MockEvent;
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

        String in1 = TextCommandUnderTest.generateTextCommandExit(TextCommandUnderTest.PROMPT_UNDER_TEST_EXIT_LOOP);
        String out2 = getExpectedOutputExitInputLoop();

        System.setIn(buildCommandInputStream(in0, in1));
        ui.textCommandLoop(tm, null);

        String expectedOutput = buildExpectedResponse(out0, out1, out2);
        assertEquals(expectedOutput, this.getOutput());

    }
}
