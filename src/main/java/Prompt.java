import interfaces.Printable;
import interfaces.Promptable;

import java.util.Iterator;

public class Prompt<T extends Printable> implements Promptable {
    private static final String PADDING = "    ";
    private static final String HORIZONTAL_LINE = "_________________________________________";
    private static final String START = "Hello! I'm Duke\n    What can I do for you?";
    private static final String END = "Bye. Hope to see you again soon!";
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
    public String add(Printable printable) {
        return formatOutput(formatLine(ADD + printable.toString()));
    }

    @Override
    public String list(Iterable inputs) {
        String output = "";
        int count = 1;
        Iterator<T> iter = inputs.iterator();
        while (iter.hasNext()) {
            output += formatLine(count + ". " + iter.next().toStatusString());
            count++;
        }
        return formatOutput(output);
    }

    @Override
    public String list(Printable[] inputs) {
        String output = "";
        for (int i=0;i < inputs.length;i++) {
            output += formatLine((i+1) + ". " + inputs[i].toStatusString());
        }
        return output;
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