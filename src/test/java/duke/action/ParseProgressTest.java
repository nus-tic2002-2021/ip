package duke.action;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ParseProgressTest {

    @Test
    @Disabled("Unknown JUnit behaviour")
    void testSplitStringIntoSentence() {
        String input = "HelloWorld";
        String[] expected = {"HelloWorld"};
        assertEquals(expected, input);
    }

    @Test
    @Disabled("Unknown JUnit behaviour")
    void splitSentenceByBarSeparator() {
        String input = "HelloWorld";
        String[] expected = {"HelloWorld"};
        assertEquals(expected, input);
    }
}
