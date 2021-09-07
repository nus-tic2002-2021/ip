package duke.coronet.orchestra;

import duke.coronet.command.Command;
import duke.coronet.command.commandFactory.UxCommandFactory;
import duke.coronet.command.errorCommand.CommandExecutionError;
import duke.coronet.command.errorCommand.CommandUnknownRequest;
import duke.coronet.command.taskCommand.taskQuery.CommandListAll;
import duke.coronet.manager.FileResourceManager;
import duke.coronet.manager.TaskManager;
import duke.coronet.manager.UxManager;

import static duke.coronet.dukeUtility.validator.TextCommandValidator.*;

public class OrchestratorLevel04 extends AbstractOrchestrator {

    public static void main(String[] args) throws Exception {
        new OrchestratorLevel04(new TaskManager(), null, new UxManager(System.out)).run();
    }

    public OrchestratorLevel04(TaskManager tm, FileResourceManager frm, UxManager uxMgr) {
        super(tm, frm, uxMgr);
        uxMgr.setUxCommandFactory(new UxCommandFactory() {
            @Override
            public Command executeTextCommand(String text, TaskManager taskManager, FileResourceManager frm) {
                try {
                    if (isRequestExitLoop(text)) {
                        return this.executeCommandExitLoop();
                    } else if (isRequestList(text)) {
                        return new CommandListAll(taskManager);
                    } else if (isRequestMarkTaskAsDone(text)) {
                        return this.executeCommandMarkTaskAsDone(text, taskManager);
                    } else if(isRequestAddToDo(text)){
                        return this.executeCommandAddToDo(text, taskManager);
                    } else if(isRequestAddDeadline(text)){
                        return this.executeCommandAddDeadline(text, taskManager);
                    }else if(isRequestAddEvent(text)){
                        return this.executeCommandAddEvent(text, taskManager);
                    }
                    else{
                        // default behavior
                        return new CommandUnknownRequest(text);
                    }
                } catch (Exception e) {
                    return new CommandExecutionError(e, "command execution @ cli");
                }
            }
        });
    }

    @Override
    public void run() throws Exception {
        this.printEntryMessage();
        this.inputLoop();
        this.printTerminateMessage();
    }
}
