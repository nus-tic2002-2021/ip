

package duke.coronet.level;

import duke.coronet.testHelper.TestStream;
import duke.coronet.manager.UxManager;
import duke.coronet.orchestra.OrchestratorLevel00;
import org.junit.Test;

import static duke.coronet.testHelper.help.OutputUnderTest.getMsgUnderTestEntry;
import static duke.coronet.testHelper.help.OutputUnderTest.getMsgUnderTestTerminate;
import static org.junit.Assert.assertEquals;

/**
 * Implement an initial skeletal version of the Duke that simply greets the user and exits.
 * <p>
 * Uses OrchestratorLevel00
 *
 * @see <a href="https://nus-tic2002-2021.github.io/website/se-book-adapted/projectDuke/index.html#level-0-greet">Level-0</a>
 */
public class Increment_00_Test extends TestStream {
    /**
     * Orchestrator should display entry and exit message and terminates gently.
     */
    @Test
    public void TestGreet() {
        String expectedEntryMessage = getMsgUnderTestEntry();
        String expectedExitMessage = getMsgUnderTestTerminate();
        String expectedOutput = expectedEntryMessage + expectedExitMessage;
        new OrchestratorLevel00(null, null, new UxManager(this.getPrintStream())).run();
        assertEquals(expectedOutput, this.getOutput());
    }

}
