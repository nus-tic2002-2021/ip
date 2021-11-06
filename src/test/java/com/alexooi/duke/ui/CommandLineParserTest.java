package com.alexooi.duke.ui;

import com.alexooi.duke.commands.Command;
import com.alexooi.duke.exceptions.InvalidCommandException;
import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import java.util.ServiceLoader;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineParserTest {
    private static final CommandLineParser COMMAND_LINE_PARSER = new CommandLineParser();
    private final ServiceLoader<Command> commandLoader = ServiceLoader.load(Command.class);

    @Test
    void readInput_CommandTodoSuccess() throws InvalidCommandException, InvalidCommandFormatException {
        String input = "todo read book";
        Scanner sc = new Scanner(input).useDelimiter("\n");
        Command cmd = COMMAND_LINE_PARSER.readInput(sc, commandLoader);
        assertEquals("todo", cmd.getKeyword(), "Parser assertEquals Todo Keyword failed.");
        assertEquals("read book", cmd.getArgs(), "Parser assertEquals Todo Args failed.");
    }

    @Test
    void readInput_CommandTodoFail() {
        String input = "todo1";
        Scanner sc = new Scanner(input).useDelimiter("\n");
        assertThrows(InvalidCommandException.class, () -> COMMAND_LINE_PARSER.readInput(sc, commandLoader));
    }

    @Test
    void readInput_CommandDeadlineSuccess() throws InvalidCommandException, InvalidCommandFormatException {
        String input = "deadline return book /by 2021-09-15";
        Scanner sc = new Scanner(input).useDelimiter("\n");
        Command cmd = COMMAND_LINE_PARSER.readInput(sc, commandLoader);
        assertEquals("deadline", cmd.getKeyword(), "Parser assertEquals Deadline Keyword failed.");
        assertEquals("return book /by 2021-09-15", cmd.getArgs(), "Parser assertEquals Deadline Args failed.");
    }

    @Test
    void readInput_CommandDeadlineFail() {
        String input = "deadlinereturn book /by 2021-09-15";
        Scanner sc = new Scanner(input).useDelimiter("\n");
        assertThrows(InvalidCommandException.class, () -> COMMAND_LINE_PARSER.readInput(sc, commandLoader));
    }

    @Test
    void readInput_CommandEventSuccess() throws InvalidCommandException, InvalidCommandFormatException {
        String input = "event meet friend /at 2021-09-15";
        Scanner sc = new Scanner(input).useDelimiter("\n");
        Command cmd = COMMAND_LINE_PARSER.readInput(sc, commandLoader);
        assertEquals("event", cmd.getKeyword(), "Parser assertEquals Event Keyword failed.");
        assertEquals("meet friend /at 2021-09-15", cmd.getArgs(), "Parser assertEquals Event Args failed.");
    }

    @Test
    void readInput_CommandEventFail() {
        String input = "eventmeet friend /at 2021-09-15";
        Scanner sc = new Scanner(input).useDelimiter("\n");
        assertThrows(InvalidCommandException.class, () -> COMMAND_LINE_PARSER.readInput(sc, commandLoader));
    }
}