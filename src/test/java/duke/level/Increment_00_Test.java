

package duke.level;

import duke.Ui;
import duke.testHelper.TestStream;
import duke.testHelper.help.OutputUnderTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Implement an initial skeletal version of the Duke that simply greets the user and exits.
 * <p>
 *
 * @see <a href="https://nus-tic2002-2021.github.io/website/se-book-adapted/projectDuke/index.html#level-0-greet">Level-0</a>
 */

public class Increment_00_Test extends TestStream {
    /**
     * Orchestrator should display entry and exit message and terminates gently.
     */
    @Test
    public void TestGreetAndExit() {
        String expectedEntryMessage = OutputUnderTest.getMsgUnderTestEntry();
        String expectedExitMessage = OutputUnderTest.getMsgUnderTestTerminate();
        String expectedOutput = expectedEntryMessage + expectedExitMessage;
        Ui ui = new Ui(this.getPrintStream());
        ui.printEntryMessage();
        ui.printTerminateMessage();
        assertEquals(expectedOutput, this.getOutput());
    }

}
