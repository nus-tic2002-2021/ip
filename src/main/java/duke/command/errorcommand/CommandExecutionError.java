package duke.command.errorcommand;

import java.util.List;

import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandExecutionError extends Command {
    public CommandExecutionError(Exception e, String when) {
        super(ResponseType.ERROR_COMMAND_EXECUTION, List.of("Error during execution of command ", e.toString()));
    }

    public String getResponse(){
        return this.getArgs().get(1);
    }
}
