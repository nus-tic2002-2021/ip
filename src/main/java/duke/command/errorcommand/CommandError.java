package duke.command.errorcommand;

import java.util.List;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;


public abstract class CommandError extends Command {

    protected CommandError(ResponseType rt, List<String> args) {
        super(rt, args);
    }

    public String getErrorMessage() {
        return this.getArgs().get(1);
    }
}
