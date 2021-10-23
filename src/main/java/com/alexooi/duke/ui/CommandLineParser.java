package com.alexooi.duke.ui;

import com.alexooi.duke.commands.Command;
import com.alexooi.duke.exceptions.InvalidCommandException;
import com.alexooi.duke.interfaces.IOParser;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineParser implements IOParser<Command, Scanner> {
    public Command readInput(Scanner sc, Iterable<Command> commands)
            throws InvalidCommandException {
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
