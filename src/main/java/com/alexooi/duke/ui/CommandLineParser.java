package com.alexooi.duke.ui;

import com.alexooi.duke.commands.Command;
import com.alexooi.duke.exceptions.InvalidCommandException;
import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import com.alexooi.duke.interfaces.IOParser;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineParser implements IOParser<Command, Scanner> {
    /**
     * This function reads the user input and matches it against a list of commands.
     * It parses the keyword and arguments of the command and returns the command found for execution.
     * @param sc        Scanner object to read in input
     * @param commands  A list of commands implemented to match against the input
     * @return          The command that was detected
     * @throws InvalidCommandException          Reaches this exception if no command is found matching the input
     * @throws InvalidCommandFormatException    Reaches this exception if a command is found the but the input is invalid
     */
    public Command readInput(Scanner sc, Iterable<Command> commands)
            throws InvalidCommandException, InvalidCommandFormatException {
        String input = sc.nextLine();

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
}
