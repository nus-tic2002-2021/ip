package duke.command;

import java.util.List;

import duke.dukeutility.enums.ResponseType;


/**
 * Abstract Class for commands.
 * Commands executes core behaviors and resultant value(s) are stored for future use.
 */
public abstract class Command {
    /* Array of args to record parameters and results for task creation. */
    private List<String> args;
    /* Response type for users to identify the behavior that was executed in the command */
    private ResponseType responseType;

    protected Command(ResponseType rt, List<String> args) {
        this.setResponseType(rt);
        this.setArgs(args);
    }

    private Command() {
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    protected void setResponseType(ResponseType rt) {
        responseType = rt;
    }

    public List<String> getArgs() {
        return this.args;
    }

    protected void setArgs(List<String> inputArgs) {
        this.args = inputArgs;
    }

    public abstract String getResponse();
}
