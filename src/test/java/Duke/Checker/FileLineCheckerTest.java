package Duke.Checker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static Duke.Checker.FileLineChecker.*;

public class FileLineCheckerTest {

    @Test
    public void checkTodoLineTest() {
        assertEquals(true,CheckTodoLine(new String[]{"T", "1", "Homework"}));
        assertEquals(false, CheckTodoLine(new String[]{"", "", ""}));
    }

    @Test
    public void checkDeadlineLineTest() {
        assertEquals(true,
                CheckDeadlineLine(new String[]{"D", "0", "Submit Project", "Sunday"}));
        assertEquals(false,
                CheckDeadlineLine(new String[]{"D", "3", "Do task", "Today"}));
    }

    @Test
    public void checkEventLineTest() {
        assertEquals(true,
                CheckEventLine(new String[]{"E", "1", "Party", "Friend's house"}));
        assertEquals(false,
                CheckEventLine(new String[]{"E", "0", "", "Nowhere"}));
    }
}
