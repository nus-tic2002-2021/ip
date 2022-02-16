package Duke.Checker;

import Duke.DukeLogic.DukeException;
import org.junit.jupiter.api.Test;

import static Duke.Checker.InputChecker.isValidTodoInput;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static Duke.Checker.FileLineChecker.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileLineCheckerTest {

    @Test
    public void checkTodoLineTest() {
        assertEquals(true, isValidTodoLine(new String[]{"T", "1", "Homework", "MEDIUM"}));
        assertThrows(AssertionError.class, () -> isValidTodoLine(new String[]{"", "", ""}));
    }

    @Test
    public void checkDeadlineLineTest() {
        assertEquals(true,
                isValidDeadlineLine(new String[]{"D", "0", "Submit Project", "Sunday", "HIGH"}));
        assertEquals(false,
                isValidDeadlineLine(new String[]{"D", "3", "Do task", "Today", "LOW"}));
    }

    @Test
    public void checkEventLineTest() {
        assertEquals(true,
                isValidEventLine(new String[]{"E", "1", "Party", "Friend's house", "LOW"}));
        assertEquals(false,
                isValidEventLine(new String[]{"E", "0", "", "Nowhere", "NIL"}));
    }
}
