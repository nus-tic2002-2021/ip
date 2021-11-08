package Duke.Checker;

import Duke.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static Duke.Checker.InputChecker.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputCheckerTest {

    @Test
    public void checkValidTodoTest() throws DukeException {
        assertEquals(true, CheckValidTodo("todo task 1"));
        assertThrows(DukeException.class, () -> CheckValidTodo("tod"));
        assertThrows(DukeException.class, () -> CheckValidTodo("todo    "));
    }

    @Test
    public void checkValidDeadlineTest() throws DukeException {
        assertEquals(true, CheckValidDeadline("deadline homework /by today"));
        assertThrows(DukeException.class, () -> CheckValidDeadline("deadli"));
        assertThrows(DukeException.class, () -> CheckValidDeadline("deadline return book /by"));
        assertThrows(DukeException.class, () -> CheckValidDeadline("deadline /by tomorrow"));

    }

    @Test
    public void checkValidEventTest() throws DukeException {
        assertEquals(true, CheckValidEvent("event assembly /at school"));
        assertThrows(DukeException.class, () -> CheckValidEvent("eve"));
        assertThrows(DukeException.class, () -> CheckValidEvent("event birthday /at"));
        assertThrows(DukeException.class, () -> CheckValidEvent("event /at home"));
    }
}
