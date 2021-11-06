package duke.command.systemcommand;

import static duke.dukeutility.definition.CommandPromptsAndOptions.PROMPT_EXIT_LOOP;
import java.util.List;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;

public class CommandExitLoop extends Command {
    public CommandExitLoop() {
        super(ResponseType.EXIT_LOOP, List.of(PROMPT_EXIT_LOOP));
    }

    @Override
    public String getResponse() {
        return null;
    }
}
