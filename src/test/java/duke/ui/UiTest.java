package duke.ui;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class UiTest {

    @Test
    public String showWelcomeTest() {
        return "___________________________________________________________________\n"
                + "Hello! I'm Duke By Justin ☺\nWhat can I do for you?\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "___________________________________________________________________\n";
    }

    @Test
    public String showDividerLineTest() {
        return "___________________________________________________________________";
    }

    @Test
    public String showExitTest() { return "Bye. Hope to see you again soon!"; }

}
