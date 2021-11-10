package Duke.Checker;

import Duke.DukeLogic.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static Duke.Checker.InputChecker.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputCheckerTest {

    @Test
    public void checkValidTodoTest() throws DukeException {
        assertEquals(true, checkValidTodo("todo task 1"));
        assertThrows(DukeException.class, () -> checkValidTodo("tod"));
        assertThrows(DukeException.class, () -> checkValidTodo("todo    "));
    }

    @Test
    public void checkValidDeadlineTest() throws DukeException {
        assertEquals(true, checkValidDeadline("deadline homework /by today"));
        assertThrows(DukeException.class, () -> checkValidDeadline("deadli"));
        assertThrows(DukeException.class, () -> checkValidDeadline("deadline return book /by"));
        assertThrows(DukeException.class, () -> checkValidDeadline("deadline /by tomorrow"));

    }

    @Test
    public void checkValidEventTest() throws DukeException {
        assertEquals(true, checkValidEvent("event assembly /at school"));
        assertThrows(DukeException.class, () -> checkValidEvent("eve"));
        assertThrows(DukeException.class, () -> checkValidEvent("event birthday /at"));
        assertThrows(DukeException.class, () -> checkValidEvent("event /at home"));
    }

    @Test
    public void checkValidFindTest() throws DukeException {
        assertEquals(true, checkValidFind(new String[]{"find","task"}));
        assertThrows(DukeException.class, () -> checkValidFind(new String[]{"find"}));
        assertThrows(DukeException.class, () -> checkValidFind(new String[]{}));

    }
}
