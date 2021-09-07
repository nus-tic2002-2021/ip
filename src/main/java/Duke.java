import interfaces.Promptable;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Promptable _prompt;

    public Duke(Promptable prompt) {
        this._prompt = prompt;
    }

    public static void main(String[] args) {
        Duke d = new Duke(new Prompt());
        System.out.println(d._prompt.start());

        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList();

        while (in.hasNext()) {
            String input = in.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(d._prompt.list(tasks));
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println(d._prompt.add(newTask));
            }
        }

        System.out.println(d._prompt.exit());
    }
}
