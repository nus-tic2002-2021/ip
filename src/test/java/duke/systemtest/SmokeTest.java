package duke.systemtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Ui;
import duke.testhelper.TestStream;
import duke.testhelper.help.codeundertest.OutputUnderTest;

public class SmokeTest extends TestStream {
    /**
     * Orchestrator should display entry and exit message and terminates gently.
     */
    @Test
    public void greetAndExit() {
        String expectedEntryMessage = OutputUnderTest.getExpectedOutputEntry();
        String expectedExitMessage = OutputUnderTest.getExpectedOutputTerminate();
        String expectedOutput = expectedEntryMessage + expectedExitMessage;
        Ui ui = new Ui(this.getPrintStream());
        ui.printEntryMessage();
        ui.printTerminateMessage();
        assertEquals(expectedOutput, this.getOutput());
    }
}
