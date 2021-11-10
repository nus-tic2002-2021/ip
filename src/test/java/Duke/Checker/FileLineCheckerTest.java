package Duke.Checker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static Duke.Checker.FileLineChecker.*;

public class FileLineCheckerTest {

    @Test
    public void checkTodoLineTest() {
        assertEquals(true, checkTodoLine(new String[]{"T", "1", "Homework", "MEDIUM"}));
        assertEquals(false, checkTodoLine(new String[]{"", "", ""}));
    }

    @Test
    public void checkDeadlineLineTest() {
        assertEquals(true,
                checkDeadlineLine(new String[]{"D", "0", "Submit Project", "Sunday", "HIGH"}));
        assertEquals(false,
                checkDeadlineLine(new String[]{"D", "3", "Do task", "Today", "LOW"}));
    }

    @Test
    public void checkEventLineTest() {
        assertEquals(true,
                checkEventLine(new String[]{"E", "1", "Party", "Friend's house", "LOW"}));
        assertEquals(false,
                checkEventLine(new String[]{"E", "0", "", "Nowhere", "NIL"}));
    }
}
