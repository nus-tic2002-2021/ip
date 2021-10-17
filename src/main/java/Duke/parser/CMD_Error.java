package Duke.parser;

import Duke.exception.UnknownSyntaxException;

public class CMD_Error extends CMD{
    public CMD_Error(String command) {
        throw new UnknownSyntaxException(command);
    }
}
