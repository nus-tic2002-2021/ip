package duke;

import duke.ui.*;

import org.junit.jupiter.api.Test;

public class DukeTest {

    private UiTest uiTest;

    public DukeTest() { uiTest = new UiTest(); }

    @Test
    public void runTest() {}

    public static void main(String[] args) {
        new DukeTest().runTest();
    }

}
