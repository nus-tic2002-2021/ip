import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static String printTasks(ArrayList<Task> tasks) {
        ArrayList<String> outputs = new ArrayList<String>();

        for (Task t : tasks) {
            outputs.add(t.getDescription());
        }

        return DukePrompt.getPrompt(DukePrompt.Prompts.MANUAL, outputs);
    }

    public static void main(String[] args) {
        System.out.println(DukePrompt.getPrompt(DukePrompt.Prompts.START));

        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        while (in.hasNext()) {
            String input = in.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(printTasks(tasks));
            } else {
                System.out.println(DukePrompt.getPrompt(DukePrompt.Prompts.ADD, input));
                tasks.add(new Task(input));
            }
        }

        System.out.println(DukePrompt.getPrompt(DukePrompt.Prompts.END));
    }
}
