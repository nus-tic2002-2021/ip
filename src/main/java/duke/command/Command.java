package duke.command;

import duke.dukeUtility.enums.ResponseType;

import java.util.List;

/**
 * Abstract Class for commands.
 * Commands executes core behaviors and resultant value(s) are stored for future use.
 */
public abstract class Command {
    /* Array of args to record parameters and results for task creation. */
    private List<String> _args;
    /* Response type for users to identify the behavior that was executed in the command */
    private ResponseType _responseType;

    protected Command(ResponseType rt, List<String> args) {
        this.setResponseType(rt);
        this.setArgs(args);
    }
    private Command() {
    }
    public ResponseType getResponseType() {
        return _responseType;
    }
    protected void setResponseType(ResponseType rt) {
        _responseType = rt;
    }
    public List<String> getArgs() {
        return this._args;
    }
    private void setArgs(List<String> _args) {
        this._args = _args;
    }
}