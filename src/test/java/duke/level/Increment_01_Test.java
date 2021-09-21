package duke.level;

import duke.Main;
import duke.Ui;
import duke.testHelper.TestStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

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
public class Increment_01_Test extends TestStream {

    @Test
    public void TestLevel1GreetEchoExit() throws Exception {
        StringBuilder commandBuilder = new StringBuilder();

        String echoText1 = "shouldEchoThisBack";
        String echoText2 = "shouldECHOTHISBACK";

        String echoCommand1 = generateTextCommandFullTextAsEcho(echoText1);
        String echoCommand2 = generateTextCommandFullTextAsEcho(echoText2);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        commandBuilder.append(echoCommand1);
        commandBuilder.append(echoCommand2);
        commandBuilder.append(exitCommand);
        System.setIn(new ByteArrayInputStream(commandBuilder.toString().getBytes()));

        String expectedExitMessage = getMsgUnderTestTerminate();

        String expectedOutputResponse = getMsgUnderTestEntry() + getMsgUnderTestBeginInputLoop() + getMsgUnderTestResponseEcho(echoText1) + getMsgUnderTestResponseEcho(echoText2) + getMsgUnderTestExitLoop() + expectedExitMessage;
        Main.run(this.getPrintStream());

        assertEquals(expectedOutputResponse, this.getOutput());
    }
}