package parser;

import commands.*;
import exceptions.DukeException;
import org.junit.jupiter.api.Test;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage("/data/test");
    private final Ui ui = new Ui();

    @Test
    public void parse_validListCommand_success() {
        Command cmd = Parser.parse("list");
        assertEquals(ListCommand.class, cmd.getClass());
    }

    @Test
    public void parse_validHelpCommand_success() {
        Command cmd = Parser.parse("help");
        assertEquals(HelpCommand.class, cmd.getClass());
    }

    @Test
    public void parse_validAddTodoCommand_success() {
        Command cmd = Parser.parse("todo do something");
        assertEquals(AddCommand.class, cmd.getClass());
    }

    @Test
    public void parse_validAddDeadlineCommand_success() {
        Command cmd = Parser.parse("deadline do something /by 2021-10-18 0000");
        assertEquals(AddCommand.class, cmd.getClass());
    }

    @Test
    public void parse_addDeadlineNullDescription_returnInvalidCmd() {
        Command cmd = Parser.parse("deadline /by 2021-10-18 1200");
        assertEquals(InvalidCommand.class, cmd.getClass());
        try {
            cmd.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Task cannot be added. " +
                    "\nDeadline or description is missing.", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_addDeadlineWhitespaceDescription_returnInvalidCmd() {
        Command cmd = Parser.parse("deadline    /by 2021-10-18 1200");
        assertEquals(InvalidCommand.class, cmd.getClass());
        try {
            cmd.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Task cannot be added. " +
                    "\nDescription is missing.", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_addDeadlineWrongFormat_returnInvalidCmd() {
        Command cmd = Parser.parse("deadline do something /by 2021-10-18");
        assertEquals(InvalidCommand.class, cmd.getClass());
        try {
            cmd.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Task cannot be added. " +
                    "\nPlease enter deadline in the format of 'yyyy-MM-dd HHmm'", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_validAddEventCommand_success() {
        Command cmd = Parser.parse("event do something /at 2021-10-18 1300 -to 2021-10-18 1400");
        assertEquals(AddCommand.class, cmd.getClass());
    }

    @Test
    public void parse_addEventNullDescription_returnInvalidCmd() {
        Command cmd = Parser.parse("event /at 2021-10-18 1300 -to 2021-10-18 1400");
        assertEquals(InvalidCommand.class, cmd.getClass());
        try {
            cmd.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Task cannot be added. \n" +
                    "Event time or description is missing.", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_addEventWhitespaceDescription_returnInvalidCmd() {
        Command cmd = Parser.parse("event     /at 2021-10-18 1300 -to 2021-10-18 1400");
        assertEquals(InvalidCommand.class, cmd.getClass());
        try {
            cmd.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Task cannot be added. \n" +
                    "Description is missing.", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_addEventWrongFormat_returnInvalidCmd() {
        Command cmd = Parser.parse("event do something /at 2021-10-18 -to 2021-10-18 1400");
        assertEquals(InvalidCommand.class, cmd.getClass());
        try {
            cmd.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Task cannot be added. \n" + "Please enter event datetime in the format of " +
                    "'yyyy-MM-dd HHmm -to yyyy-MM-dd HHmm'", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_validCompleteCommand_success() {
        Command cmd = Parser.parse("done 1");
        assertEquals(CompleteCommand.class, cmd.getClass());
    }

    @Test
    public void parse_completeMissingId_returnInvalidCmd() {
        Command cmd = Parser.parse("done");
        assertEquals(InvalidCommand.class, cmd.getClass());
        try {
            cmd.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Task id is missing.", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_validDeleteCommand_success() {
        Command cmd = Parser.parse("delete 1");
        assertEquals(DeleteCommand.class, cmd.getClass());
    }

    @Test
    public void parse_deleteMissingId_returnInvalidCmd() {
        Command cmd = Parser.parse("delete");
        assertEquals(InvalidCommand.class, cmd.getClass());
        try {
            cmd.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Task id is missing.", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_validFindCommand_success() {
        Command cmd = Parser.parse("find book");
        assertEquals(FindCommand.class, cmd.getClass());
    }

    @Test
    public void parse_findMissingKeyword_returnInvalidCmd() {
        Command cmd = Parser.parse("find");
        assertEquals(InvalidCommand.class, cmd.getClass());
        try {
            cmd.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Search keyword is missing.", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_validViewCommand_success(){
        Command cmd = Parser.parse("view 2021-10-19");
        assertEquals(ViewCommand.class, cmd.getClass());
    }

    @Test
    public void parse_viewMissingDate_returnInvalidCmd(){
        Command cmd = Parser.parse("view");
        assertEquals(InvalidCommand.class, cmd.getClass());
        try {
            cmd.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Date is missing.", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_viewWrongDateFormat_returnInvalidCmd(){
        Command cmd = Parser.parse("view 31-10-2021");
        assertEquals(InvalidCommand.class, cmd.getClass());
        try {
            cmd.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals("Please enter the date in the format of 'yyyy-MM-dd'", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_validExitCommand_success(){
        Command cmd = Parser.parse("bye");
        assertEquals(ExitCommand.class, cmd.getClass());
    }

    @Test
    public void parse_invalidCommand_success(){
        Command cmd = Parser.parse("hello");
        assertEquals(InvalidCommand.class, cmd.getClass());
    }
}
