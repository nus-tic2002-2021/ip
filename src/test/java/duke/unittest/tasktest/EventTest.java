package duke.unittest.tasktest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.TaskManager;
import duke.command.Command;
import duke.command.commandfactory.UiCommandFactory;
import duke.command.errorcommand.CommandInvalidRequestParameters;
import duke.testhelper.help.codeundertest.TextCommandUnderTest;


public class EventTest {
    @Test
    void createEventTest_expectInvalidDateStringToReturnCommandError() {
        String desc = "event1";
        String fromString = "nonsense day1";
        String toString = "asdasdasd asdasd ";
        int id = 1;
        boolean done = false;

        String textCommand = TextCommandUnderTest.generateTextCommandLineAddEvent(desc, fromString, toString);
        Command c = new UiCommandFactory().executeTextCommand(textCommand, new TaskManager(), null);

        assertTrue(c instanceof CommandInvalidRequestParameters);
    }

}
