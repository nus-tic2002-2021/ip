package duke.testHelper.help;

import java.io.ByteArrayInputStream;

public class Builder {


    public static ByteArrayInputStream buildCommandInputStream(String... commands){
        return new ByteArrayInputStream(buildString(commands).getBytes());
    }

    public static String buildString(String... commands){
        StringBuilder commandBuilder = new StringBuilder();
        for(String c : commands){
            commandBuilder.append(c);
        }
        return commandBuilder.toString();
    }
    public static String buildExpectedResponse(String... commands){

        return buildString(commands);
    }

}
