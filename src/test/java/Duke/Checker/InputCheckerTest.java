package Duke.Checker;

import Duke.DukeLogic.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static Duke.Checker.InputChecker.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputCheckerTest {

    @Test
    public void checkValidTodoTest() throws DukeException {
        assertEquals(true, isValidTodoInput("todo task 1"));
        assertThrows(DukeException.class, () -> isValidTodoInput("tod"));
        assertThrows(DukeException.class, () -> isValidTodoInput("todo    "));
    }

    @Test
    public void checkValidDeadlineTest() throws DukeException {
        assertEquals(true, isValidDeadlineInput("deadline homework /by today"));
        assertThrows(DukeException.class, () -> isValidDeadlineInput("deadli"));
        assertThrows(DukeException.class, () -> isValidDeadlineInput("deadline return book /by"));
        assertThrows(DukeException.class, () -> isValidDeadlineInput("deadline /by tomorrow"));

    }

    @Test
    public void checkValidEventTest() throws DukeException {
        assertEquals(true, isValidEventInput("event assembly /at school"));
        assertThrows(DukeException.class, () -> isValidEventInput("eve"));
        assertThrows(DukeException.class, () -> isValidEventInput("event birthday /at"));
        assertThrows(DukeException.class, () -> isValidEventInput("event /at home"));
    }

    @Test
    public void checkValidFindTest() throws DukeException {
        assertEquals(true, isValidFindInput(new String[]{"find","task"}));
        assertThrows(DukeException.class, () -> isValidFindInput(new String[]{"find"}));
        assertThrows(DukeException.class, () -> isValidFindInput(new String[]{}));

    }
}
