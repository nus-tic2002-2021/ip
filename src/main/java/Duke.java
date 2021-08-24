import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static String printTasks(ArrayList<Task> tasks) {
        String output = "";

        for (int i=0;i < tasks.size();i++) {
            output += DukePrompt.DEFAULT_PADDING + (i + 1) + ". ";
            output += tasks.get(i).toString();
            if (i < tasks.size() - 1) {
                output += System.lineSeparator();
            }
        }

        return DukePrompt.getPrompt(DukePrompt.Prompts.MANUAL, output);
    }

    public static void main(String[] args) {
        System.out.println(DukePrompt.getPrompt(DukePrompt.Prompts.START));

        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList();

        while (in.hasNext()) {
            String input = in.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(printTasks(tasks));
            } else if (input.matches("^done \\d+")) {
                String[] inputs = input.split(" ");
                int taskNum = Integer.parseInt(inputs[1]) - 1;
                tasks.get(taskNum).setDone(true);
                System.out.println(DukePrompt.getPrompt(DukePrompt.Prompts.DONE, tasks.get(taskNum).toString()));
            } else {
                System.out.println(DukePrompt.getPrompt(DukePrompt.Prompts.ADD, input));
                tasks.add(new Task(input));
            }
        }

        System.out.println(DukePrompt.getPrompt(DukePrompt.Prompts.END));
    }
}
