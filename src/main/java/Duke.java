import classes.Task;
import interfaces.Printable;
import interfaces.Promptable;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final Promptable<Task> _prompt;

    public Duke(Promptable<Task> prompt) {
        this._prompt = prompt;
    }

    public static void main(String[] args) {
        Duke d = new Duke(new Prompt());
        System.out.println(d._prompt.start());

        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (in.hasNext()) {
            String input = in.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(d._prompt.list(tasks));
            } else if (input.startsWith("done")) {
                int idx = Integer.parseInt(input.split(" ")[1]);
                tasks.get(idx - 1).setDone(true);
                System.out.println(d._prompt.done(tasks.get(idx - 1)));
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println(d._prompt.add(newTask));
            }
        }

        System.out.println(d._prompt.exit());
    }
}
