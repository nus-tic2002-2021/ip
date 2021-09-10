import classes.enums.CommandType;
import classes.tasks.Task;
import classes.tasks.TaskFactory;
import classes.tasks.TaskList;
import classes.commands.Command;
import classes.ui.Parser;
import classes.ui.Prompt;
import exceptions.InvalidCommandException;
import exceptions.InvalidCommandFormatException;
import interfaces.IOParser;
import interfaces.Promptable;

import java.util.Scanner;

public class Duke {
    private static Duke instance;
    private final Promptable<Task> prompt;
    private final IOParser<Command, Scanner> parser;

    private Duke(Promptable<Task> prompt, IOParser<Command, Scanner> parser) {
        this.prompt = prompt;
        this.parser = parser;
    }

    public static Duke getInstance() {
        if (instance == null) {
            instance = new Duke(new Prompt(), new Parser());
        }

        return instance;
    }

    public static void main(String[] args) {
        Duke main = Duke.getInstance();
        TaskList tasks = TaskList.getInstance();
        System.out.println(main.prompt.start());

        Scanner in = new Scanner(System.in);
        boolean receiveInput = true;

        while (receiveInput && in.hasNext()) {
            try {
                Command command = main.parser.readInput(in);
                if (command.getType() == CommandType.EXIT) {
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
        }

        System.out.println(main.prompt.exit());
    }
}
