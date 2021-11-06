package duke.test;
import duke.Parser;
import duke.command.DeleteCommand;
import duke.exception.InvalidException;
import duke.exception.MissReqException;
import org.junit.Test;
import static org.junit.Assert.*;
public class ParseTest {
    @Test
    public void parseTest() throws MissReqException, InvalidException {
        assertEquals(0, ((DeleteCommand)Parser.parse("delete 1")).idx);
        assertEquals("delete 1", Parser.parse("delete 1").req);
        assertEquals("list", Parser.parse("list").req);
    }
}
