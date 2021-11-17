package com.alexooi.duke;

import com.alexooi.duke.commands.Command;
import com.alexooi.duke.exceptions.InvalidFileFormatException;
import com.alexooi.duke.storage.FileStorage;
import com.alexooi.duke.tasks.Task;
import com.alexooi.duke.tasks.TaskList;
import com.alexooi.duke.ui.CommandLineParser;
import com.alexooi.duke.ui.OutputFormatter;
import com.alexooi.duke.exceptions.InvalidCommandException;
import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import com.alexooi.duke.interfaces.Parser;
import com.alexooi.duke.interfaces.OutputFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static Duke instance;
    private final OutputFormat<Task> prompt;
    private final Parser<String, Command> parser;

    private Duke(OutputFormat<Task> prompt, Parser<String, Command> parser) {
        this.prompt = prompt;
        this.parser = parser;
    }

    public static Duke getInstance() {
        if (instance == null) {
            instance = new Duke(new OutputFormatter(), new CommandLineParser());
        }

        return instance;
    }

    public static void main(String[] args) {
        final String STATE_PATH = "state.txt";
        final String ARCHIVE_PATH = "archive.txt";

        Duke main = Duke.getInstance();
        FileStorage state = new FileStorage(STATE_PATH);
        FileStorage archive = new FileStorage(ARCHIVE_PATH);

        TaskList tasks = TaskList.getInstance(state, archive);
        System.out.println(main.prompt.start());

        // Load previous task list state
        BufferedReader stateInput = state.load();
        try {
            tasks.load(stateInput);
        } catch (IOException ioe) {
            main.prompt.error(ioe.getMessage());
        } catch (InvalidFileFormatException iffe) {
            main.prompt.error(iffe.getErrorHeader(), iffe.getMessage());
        }

        Scanner in = new Scanner(System.in);
        boolean isReceivingInput = true;

        while (isReceivingInput && in.hasNext()) {
            try {
                String input = in.nextLine();
                Command command = main.parser.parseInput(input);
                if (command.isExit()) {
                    isReceivingInput = false;
                } else {
                    String output = command.execute(tasks, main.prompt);
                    System.out.println(output);
                }
            } catch (InvalidCommandException ice) {
                System.out.println(main.prompt.error(ice.getErrorHeader(), ice.getMessage()));
            } catch (InvalidCommandFormatException icfe) {
                System.out.println(main.prompt.error(icfe.getErrorHeader(), icfe.getMessage()));
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                System.out.println(main.prompt.error(ex.getMessage()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            tasks.save();
        }

        System.out.println(main.prompt.exit());
    }
}
