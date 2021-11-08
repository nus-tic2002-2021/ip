package com.alexooi.duke.ui;

import com.alexooi.duke.commands.Command;
import com.alexooi.duke.exceptions.InvalidCommandException;
import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import com.alexooi.duke.interfaces.Parser;

import java.util.ServiceLoader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineParser implements Parser<String, Command> {
    final ServiceLoader<Command> commands = ServiceLoader.load(Command.class);

    /**
     * This function reads the user input and matches it against a list of commands.
     * It parses the keyword and arguments of the command and returns the command found for execution.
     * @param input     The input string to parse
     * @return          The command that was detected
     * @throws InvalidCommandException          Reaches this exception if no command is found matching the input
     * @throws InvalidCommandFormatException    Reaches this exception if a command is found the but the input is invalid
     */
    public Command parseInput(String input)
            throws Exception {

        for (Command cmd : commands) {
            Pattern servicePattern = cmd.getRegexPattern();
            Matcher patternMatcher = servicePattern.matcher(input);

            if (patternMatcher.matches()) {
                String keyword = patternMatcher.group(1);
                cmd.setKeyword(keyword);
                if (patternMatcher.groupCount() > 1) {
                    String args = patternMatcher.group(2);
                    cmd.setArgs(args);
                }
                return cmd.build();
            }
        }

        throw new InvalidCommandException(InvalidCommandException.ERROR_GENERIC);
    }

    /**
     * Note: This function is not in use, but has been added here due to the interface.
     * @param input String to validate
     * @return
     */
    @Override
    public boolean isValidInput(String input) {
        return false;
    }
}
