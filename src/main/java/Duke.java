import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(DukePrompt.getPrompt(DukePrompt.Prompts.START));

        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String input = in.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            } else {
                System.out.println(DukePrompt.getPrompt(DukePrompt.Prompts.MANUAL, input));
            }
        }

        System.out.println(DukePrompt.getPrompt(DukePrompt.Prompts.END));
    }
}
