package duke;


import duke.command.Command;
import duke.command.commandFactory.UiCommandFactory;
import duke.command.errorCommand.CommandExecutionError;

import java.io.PrintStream;

import static duke.dukeUtility.validator.TextCommandValidator.isRequestExitLoop;

public class Main {

    public static void main(String[] args) throws Exception {
        Main.run(System.out);
    }

    public static void run(PrintStream out) throws Exception{
        Ui ui = new Ui(out);
        ui.setUiCommandFactory(new UiCommandFactory(){
            @Override
            public Command executeTextCommand(String text) {
                try {
                    if (isRequestExitLoop(text)) {
                        return this.executeCommandExitLoop();
                    } else {
                        return this.executeCommandEcho(text);
                    }
                } catch (Exception e) {
                    return new CommandExecutionError(e, "command execution @ cli");
                }
            }
        });
        ui.printEntryMessage();
        ui.textCommandLoop();
        ui.printTerminateMessage();
    }
}
