package duke;

import duke.ui.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    private UiTest uiTest;

    private final String showWelcomeTestString = "___________________________________________________________________\n"
            + "Hello! I'm Duke By Justin ☺\nWhat can I do for you?\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "___________________________________________________________________\n";
    private final String showDividerLineTestString = "___________________________________________________________________";
    private final String showExitTestString = "Bye. Hope to see you again soon!";

    public DukeTest() {
        uiTest = new UiTest();
    }

    @Test
    public void runTest() {
        assertEquals(showWelcomeTestString, uiTest.showWelcomeTest());
        assertEquals(showDividerLineTestString, uiTest.showDividerLineTest());
        assertEquals(showExitTestString, uiTest.showExitTest());
    }

    public static void main(String[] args) {
        new DukeTest().runTest();
    }

}
