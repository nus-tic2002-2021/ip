import java.util.ArrayList;

public class DukePrompt {
    public enum Prompts {
        START,
        END,
        ADD,
        DONE,
        MANUAL
    }

    public static final String DEFAULT_PADDING = "    ";
    private static final String HORIZONTAL_LINE = DEFAULT_PADDING + "_________________________________________";
    private static final String START = DEFAULT_PADDING + "Hello! I'm Duke\n    What can I do for you?";
    private static final String END = DEFAULT_PADDING + "Bye. Hope to see you again soon!";
    private static final String TASK_ADD = DEFAULT_PADDING + "added: ";
    private static final String TASK_DONE = DEFAULT_PADDING + "Nice! I've marked this task as done:\n" + DEFAULT_PADDING + "   ";

    public static String getPrompt(Prompts decision) {
        return getPrompt(decision, "");
    }

    public static String getPrompt(Prompts decision, String man) {
        String output = "";

        output += HORIZONTAL_LINE + System.lineSeparator();

        switch (decision) {
        case START:
            output += START;
            break;
        case END:
            output += END;
            break;
        case ADD:
            output += TASK_ADD + man;
            break;
        case DONE:
            output += TASK_DONE + man;
            break;
        default:
            output += man;
            break;
        }

        output += System.lineSeparator() + HORIZONTAL_LINE;

        return output;
    }
}