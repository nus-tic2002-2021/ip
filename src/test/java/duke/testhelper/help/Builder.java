package duke.testhelper.help;

import java.io.ByteArrayInputStream;

public class Builder {


    public static ByteArrayInputStream buildCommandInputStream(String... commands) {
        return new ByteArrayInputStream(buildString(commands).getBytes());
    }

    /**
     * Helper to combine array of strings.
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

    /**
     * Helper to build expected output response.
     * @param responses
     * @return output response
     */
    public static String buildExpectedResponse(String... responses) {
        return buildString(responses);
    }

}
