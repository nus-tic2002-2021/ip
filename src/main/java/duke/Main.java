package duke;


import duke.command.Command;
import duke.command.commandFactory.UiCommandFactory;
import duke.command.errorCommand.CommandExecutionError;
import duke.command.errorCommand.CommandUnknownRequest;
import duke.command.taskCommand.taskQuery.CommandListAll;

import java.io.PrintStream;

import static duke.dukeUtility.validator.TextCommandValidator.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Main.run(System.out, new TaskManager());
    }

    public static void run(PrintStream out, TaskManager taskManager) throws Exception {
        Ui ui = new Ui(out);
        ui.setUiCommandFactory(new UiCommandFactory() {
            @Override
            public Command executeTextCommand(String text, TaskManager taskManager) {
                try {
                    if (isRequestExitLoop(text)) {
                        return this.executeCommandExitLoop();
                    } else if (isRequestList(text)) {
                        return new CommandListAll(taskManager);
                    } else if (isRequestMarkTaskAsDone(text)) {
                        return this.executeCommandMarkTaskAsDone(text, taskManager);
                    } else if (isRequestAddToDo(text)) {
                        return this.executeCommandAddToDo(text, taskManager);
                    } else if(isRequestAddEvent(text)){
                        return this.executeCommandAddEvent(text, taskManager);
                    }else {
                        return new CommandUnknownRequest(text);
                    }
                } catch (Exception e) {
                    return new CommandExecutionError(e, "command execution @ cli");
                }
            }
        });
        ui.printEntryMessage();
        ui.textCommandLoop(taskManager);
        ui.printTerminateMessage();
    }
}
