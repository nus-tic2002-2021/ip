package duke.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(originalOut);
    }

    @Test
    public void showWelcomeTest() {
        new Ui().showWelcome();
        assertEquals("___________________________________________________________________\n"
                        + "Hello! I'm Duke By Justin ☺\nWhat can I do for you?\n"
                        + " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n"
                        + "___________________________________________________________________\n",
                outContent.toString());
    }

    @Test
    public void showDividerLineTest() {
        new Ui().showDividerLine();
        assertEquals("___________________________________________________________________",
                outContent.toString().trim());
    }

    @Test
    public void showExitTest() {
        new Ui().showExit();
        assertEquals("Bye. Hope to see you again soon!",
                outContent.toString().trim());
    }

}
