import java.util.ArrayList;

public class DukePrompt {
    public enum Prompts {
        START,
        END,
        ADD,
        MANUAL
    }

    private static final String PADDING = "    ";
    private static final String HORIZONTAL_LINE = PADDING + "_________________________________________";
    private static final String START = "Hello! I'm Duke\n    What can I do for you?";
    private static final String END = "Bye. Hope to see you again soon!";
    private static final String ADD = "added: ";

    public static String getPrompt(Prompts decision) {
        return getPrompt(decision, "");
    }

    public static String getPrompt(Prompts decision, String man) {
        String output = "";

        output += HORIZONTAL_LINE + System.lineSeparator() + PADDING;

        switch (decision) {
            case START:
                output += START;
                break;
            case END:
                output += END;
                break;
            case ADD:
                output += ADD + man;
                break;
            default:
                output += man;
                break;
        }

        output += System.lineSeparator() + HORIZONTAL_LINE;

        return output;
    }

    public static String getPrompt(Prompts decision, ArrayList<String> outputs) {
        String formattedInput = "";

        for (int i=0;i < outputs.size();i++) {
            if (i > 0) {
                formattedInput += System.lineSeparator() + PADDING;
            }
            formattedInput += (i + 1) + ". " + outputs.get(i);
        }

        return getPrompt(Prompts.MANUAL, formattedInput);
    }
}