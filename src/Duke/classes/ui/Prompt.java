package Duke.classes.ui;

import Duke.enums.TaskType;
import Duke.classes.tasks.Deadline;
import Duke.classes.tasks.Event;
import Duke.classes.tasks.Task;
import Duke.classes.tasks.Todo;
import Duke.classes.interfaces.Promptable;

public class Prompt implements Promptable<Task> {
    private static final String PADDING = "    ";
    private static final String HORIZONTAL_LINE = "_________________________________________";
    private static final String START = "Hello! I'm Duke\n    What can I do for you?";
    private static final String END = "Bye. Hope to see you again soon!";
    private static final String DONE = "Nice! I've marked this task as done:";
    private static final String ADD = "added: ";

    @Override
    public String start() {
        return formatOutput(formatLine(START));
    }

    @Override
    public String add(Task printable, int length) {
        StringBuilder output = new StringBuilder();
        output.append(formatLine(ADD + printable.toString()));
        output.append(formatLine("Now you have " + length + " task" + ((length > 1) ? "s" : "") + " in the list."));

        return formatOutput(output.toString());
    }

    @Override
    public String done(Task printable) {
        StringBuilder output = new StringBuilder();
        output.append(formatLine(DONE) + formatLine("   " + printable.toStatusString()));

        return formatOutput(output.toString());
    }

    @Override
    public String list(Iterable<Task> inputs) {
        StringBuilder output = new StringBuilder();
        int count = 1;
        for (Task input : inputs) {
            String taskStr = "";
            if (input.getType() == TaskType.DEADLINE) {
                Deadline down = (Deadline) input;
                taskStr = down.toStatusString();
            } else if (input.getType() == TaskType.EVENT) {
                Event down = (Event) input;
                taskStr = down.toStatusString();
            } else if (input.getType() == TaskType.TODO) {
                Todo down = (Todo) input;
                taskStr = down.toStatusString();
            }
            output.append(formatLine(count + ". " + taskStr));
            count++;
        }
        return formatOutput(output.toString());
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