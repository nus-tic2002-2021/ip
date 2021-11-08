package com.alexooi.duke.ui;

import com.alexooi.duke.commands.Command;
import com.alexooi.duke.exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineParserTest {
    private static final CommandLineParser COMMAND_LINE_PARSER = new CommandLineParser();

    @Test
    void readInput_CommandTodoSuccess() throws Exception {
        String input = "todo read book";
        Command cmd = COMMAND_LINE_PARSER.parseInput(input);
        assertEquals("todo", cmd.getKeyword(), "Parser assertEquals Todo Keyword failed.");
        assertEquals("read book", cmd.getArgs(), "Parser assertEquals Todo Args failed.");
    }

    @Test
    void readInput_CommandTodoFail() {
        String input = "todo1";
        assertThrows(InvalidCommandException.class, () -> COMMAND_LINE_PARSER.parseInput(input));
    }

    @Test
    void readInput_CommandDeadlineSuccess() throws Exception {
        String input = "deadline return book /by 2021-09-15";
        Command cmd = COMMAND_LINE_PARSER.parseInput(input);
        assertEquals("deadline", cmd.getKeyword(), "Parser assertEquals Deadline Keyword failed.");
        assertEquals("return book /by 2021-09-15", cmd.getArgs(), "Parser assertEquals Deadline Args failed.");
    }

    @Test
    void readInput_CommandDeadlineFail() {
        String input = "deadlinereturn book /by 2021-09-15";
        assertThrows(InvalidCommandException.class, () -> COMMAND_LINE_PARSER.parseInput(input));
    }

    @Test
    void readInput_CommandEventSuccess() throws Exception {
        String input = "event meet friend /at 2021-09-15";
        Command cmd = COMMAND_LINE_PARSER.parseInput(input);
        assertEquals("event", cmd.getKeyword(), "Parser assertEquals Event Keyword failed.");
        assertEquals("meet friend /at 2021-09-15", cmd.getArgs(), "Parser assertEquals Event Args failed.");
    }

    @Test
    void readInput_CommandEventFail() {
        String input = "eventmeet friend /at 2021-09-15";
        assertThrows(InvalidCommandException.class, () -> COMMAND_LINE_PARSER.parseInput(input));
    }
}