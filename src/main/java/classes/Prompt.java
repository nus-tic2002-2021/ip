import classes.Task;
import interfaces.Promptable;

public class Prompt implements Promptable<Task> {
    private static final String PADDING = "    ";
    private static final String HORIZONTAL_LINE = "_________________________________________";
    private static final String START = "Hello! I'm Duke\n    What can I do for you?";
    private static final String END = "Bye. Hope to see you again soon!";
    private static final String DONE = "Nice! I've marked this task as done:";
    private static final String ADD = "added: ";

    public enum Prompts {
        START,
        END,
        ADD,
        MANUAL
    }

    @Override
    public String start() {
        return formatOutput(formatLine(START));
    }

    @Override
    public String add(Task printable) {
        return formatOutput(formatLine(ADD + printable.toString()));
    }

    @Override
    public String done(Task printable) {
        return formatOutput(formatLine(DONE) + formatLine("   " + printable.toStatusString()));
    }

    @Override
    public String list(Iterable<Task> inputs) {
        StringBuilder output = new StringBuilder();
        int count = 1;
        for (Task input : inputs) {
            output.append(formatLine(count + ". " + input.toStatusString()));
            count++;
        }
        return formatOutput(output.toString());
    }

    @Override
    public String list(Task[] inputs) {
        StringBuilder output = new StringBuilder();
        for (int i=0;i < inputs.length;i++) {
            output.append(formatLine((i + 1) + ". " + inputs[i].toStatusString()));
        }
        return output.toString();
    }

    @Override
    public String exit() {
        return formatOutput(formatLine(END));
    }

    private String formatLine(String output) {
        return PADDING + output + System.lineSeparator();
    }

    private String formatOutput(String output) {
        return formatLine(HORIZONTAL_LINE) + output + PADDING + HORIZONTAL_LINE;
    }
}