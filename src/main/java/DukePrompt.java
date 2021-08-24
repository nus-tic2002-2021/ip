public class DukePrompt {
    public enum Prompts {
        START,
        END,
        MANUAL
    }

    private static final String HORIZONTAL_LINE = "    _________________________________________";
    private static final String START = "    Hello! I'm Duke\n    What can I do for you?\n";
    private static final String END = "    Bye. Hope to see you again soon!\n";

    public static String getPrompt(Prompts decision) {
        return getPrompt(decision, null);
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
            default:
                output += "    " + man + System.lineSeparator();
                break;
        }

        output += HORIZONTAL_LINE;

        return output;
    }
}