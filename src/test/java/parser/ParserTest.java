package parser;

import commands.*;
import org.junit.jupiter.api.Test;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parse_validListCommand_success(){
        Parser p = new Parser();
        Command cmd = p.parse("list");
        assertEquals(ListCommand.class, cmd.getClass());
    }

    @Test
    public void parse_validAddCommand_toDo_success(){
        Parser p = new Parser();
        Command cmd = p.parse("todo do something");
        assertEquals(AddCommand.class, cmd.getClass());
    }

    @Test
    public void parse_validAddCommand_deadline_success(){
        Parser p = new Parser();
        Command cmd = p.parse("deadline do something /by 2021-10-18 0000");
        assertEquals(AddCommand.class, cmd.getClass());
    }

    @Test
    public void parse_validAddCommand_event_success(){
        Parser p = new Parser();
        Command cmd = p.parse("event do something /at 2021-10-18 1300 to 2021-10-18 1400");
        assertEquals(AddCommand.class, cmd.getClass());
    }

    @Test
    public void parse_validDeleteCommand_success(){
        Parser p = new Parser();
        Command cmd = p.parse("delete 1");
        assertEquals(DeleteCommand.class, cmd.getClass());
    }

    @Test
    public void parse_validFindCommand_success(){
        Parser p = new Parser();
        Command cmd = p.parse("find book");
        assertEquals(FindCommand.class, cmd.getClass());
    }

    @Test
    public void parse_validViewCommand_success(){
        Parser p = new Parser();
        Command cmd = p.parse("view 2021-10-19");
        assertEquals(ViewCommand.class, cmd.getClass());
    }

    @Test
    public void parse_validExitCommand_success(){
        Parser p = new Parser();
        Command cmd = Parser.parse("bye");
        assertEquals(ExitCommand.class, cmd.getClass());
    }

    @Test
    public void parse_invalidCommand_success(){
        Parser p = new Parser();
        Command cmd = Parser.parse("this is wrong");
        assertEquals(InvalidCommand.class, cmd.getClass());
    }
}
