package duke.dukeutility;

public class helper {

    /**
     * Helper to combine array of strings.
     *
     * @param commands
     * @return
     */
    public static String buildString(String... commands) {
        StringBuilder commandBuilder = new StringBuilder();
        for (String c : commands) {
            commandBuilder.append(c);
        }
        return commandBuilder.toString();
    }
}
