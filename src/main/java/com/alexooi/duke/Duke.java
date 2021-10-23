package com.alexooi.duke;

import com.alexooi.duke.commands.Command;
import com.alexooi.duke.storage.FileStorage;
import com.alexooi.duke.tasks.Task;
import com.alexooi.duke.tasks.TaskList;
import com.alexooi.duke.ui.CommandLineParser;
import com.alexooi.duke.ui.OutputFormatter;
import com.alexooi.duke.exceptions.InvalidCommandException;
import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import com.alexooi.duke.interfaces.IOParser;
import com.alexooi.duke.interfaces.OutputFormat;

import java.util.Scanner;
import java.util.ServiceLoader;

public class Duke {
    private static Duke instance;
    private final OutputFormat<Task> prompt;
    private final IOParser<Command, Scanner> parser;

    private Duke(OutputFormat<Task> prompt, IOParser<Command, Scanner> parser) {
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
        Duke main = Duke.getInstance();
        FileStorage client = new FileStorage("state.txt");

        TaskList tasks = TaskList.getInstance(client);
        ServiceLoader<Command> commandLoader = ServiceLoader.load(Command.class);
        System.out.println(main.prompt.start());

        Scanner in = new Scanner(System.in);
        boolean receiveInput = true;

        while (receiveInput && in.hasNext()) {
            try {
                Command command = main.parser.readInput(in, commandLoader);
                if (command.isExit()) {
                    receiveInput = false;
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
