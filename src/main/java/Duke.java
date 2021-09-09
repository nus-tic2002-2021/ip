import classes.tasks.Task;
import classes.tasks.TaskFactory;
import classes.ui.Command;
import classes.ui.Parser;
import classes.ui.Prompt;
import exceptions.InvalidCommandException;
import exceptions.InvalidCommandFormatException;
import interfaces.IOParser;
import interfaces.Promptable;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final Promptable<Task> prompt;
    private final IOParser<Command, Scanner> parser;

    public Duke(Promptable<Task> prompt, Parser parser) {
        this.prompt = prompt;
        this.parser = parser;
    }

    public static void main(String[] args) {
        Duke d = new Duke(new Prompt(), new Parser());
        System.out.println(d.prompt.start());

        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        boolean receiveInput = true;

        while (receiveInput && in.hasNext()) {
            try {
                Command command = d.parser.readInput(in);
                String output = "";
                switch (command.getType()) {
                case ADD:
                    Task newTask = TaskFactory.getInstance(command);
                    tasks.add(newTask);
                    output = d.prompt.add(newTask, tasks.size());
                    break;
                case COMPLETE:
                    int idx = Integer.parseInt(command.getArgs());
                    Task doneTask = tasks.get(idx - 1);
                    doneTask.setDone(true);
                    output = d.prompt.done(doneTask);
                    break;
                case LIST:
                    output = d.prompt.list(tasks);
                    break;
                case EXIT:
                    receiveInput = false;
                    break;
                }
                System.out.println(output);
            } catch (InvalidCommandException ice) {
                System.out.println(d.prompt.error(ice.getErrorHeader(), ice.getMessage()));
            } catch (InvalidCommandFormatException icfe) {
                System.out.println(d.prompt.error(icfe.getErrorHeader(), icfe.getMessage()));
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                System.out.println(d.prompt.error(ex.getMessage()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        System.out.println(d.prompt.exit());
    }
}
