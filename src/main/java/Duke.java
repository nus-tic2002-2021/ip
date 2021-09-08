import classes.*;
import classes.tasks.*;
import exceptions.InvalidCommandException;
import exceptions.InvalidCommandFormatException;
import interfaces.Promptable;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final Promptable<Task> prompt;
    private final Parser parser;

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
                switch(command.getType()) {
                    case ADD:
                        Task newTask = TaskFactory.getInstance(command);
                        tasks.add(newTask);
                        System.out.println(d.prompt.add(newTask, tasks.size()));
                        break;
                    case COMPLETE:
                        int idx = Integer.parseInt(command.getArgs());
                        Task doneTask = tasks.get(idx - 1);
                        doneTask.setDone(true);
                        System.out.println(d.prompt.done(doneTask));
                        break;
                    case LIST:
                        System.out.println(d.prompt.list(tasks));
                        break;
                    case EXIT:
                        receiveInput = false;
                        break;
                }
            } catch (InvalidCommandException ice) {
                System.out.println("Invalid Command Exception.");
            } catch (NumberFormatException nfe) {
                System.out.println("Number Format Exception.");
            } catch (IndexOutOfBoundsException ioobe) {
                System.out.println("IndexOutOfBoundsException.");
            } catch (InvalidCommandFormatException icfe) {
                System.out.println("Invalid Command Format Exception.");
            }
        }

        System.out.println(d.prompt.exit());
    }
}
