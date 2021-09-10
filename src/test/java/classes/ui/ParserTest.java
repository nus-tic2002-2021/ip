package classes.ui;

import classes.commands.Command;
import classes.enums.CommandType;
import exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    private static Parser parser = new Parser();

    @Test
    void readInput_CommandTodoSuccess() throws InvalidCommandException {
        String input = "todo read book";
        Scanner sc = new Scanner(input).useDelimiter("\n");
        Command cmd = parser.readInput(sc);
        assertEquals(CommandType.ADD, cmd.getType(), "Parser assertEquals Todo CommandType ADD failed.");
        assertEquals("todo", cmd.getKeyword(), "Parser assertEquals Todo Keyword failed.");
        assertEquals("read book", cmd.getArgs(), "Parser assertEquals Todo Args failed.");
    }

    @Test
    void readInput_CommandTodoFail() {
        String input = "todo1";
        Scanner sc = new Scanner(input).useDelimiter("\n");
        assertThrows(InvalidCommandException.class, () -> {
            parser.readInput(sc);
        });
    }

    @Test
    void readInput_CommandEventSuccess() throws InvalidCommandException {
        String input = "event meet friend /at 2021-09-15";
        Scanner sc = new Scanner(input).useDelimiter("\n");
        Command cmd = parser.readInput(sc);
        assertEquals(CommandType.ADD, cmd.getType(), "Parser assertEquals Event CommandType ADD failed.");
        assertEquals("event", cmd.getKeyword(), "Parser assertEquals Event Keyword failed.");
        assertEquals("meet friend /at 2021-09-15", cmd.getArgs(), "Parser assertEquals Event Args failed.");
    }

    @Test
    void readInput_CommandEventFail() {
        String input = "eventmeet friend /at 2021-09-15";
        Scanner sc = new Scanner(input).useDelimiter("\n");
        assertThrows(InvalidCommandException.class, () -> {
            parser.readInput(sc);
        });
    }
}