package duke.coronet.orchestra;

import duke.coronet.command.Command;
import duke.coronet.command.commandFactory.UxCommandFactory;
import duke.coronet.command.errorCommand.CommandExecutionError;
import duke.coronet.manager.FileResourceManager;
import duke.coronet.manager.TaskManager;
import duke.coronet.manager.UxManager;

import static duke.coronet.dukeUtility.validator.TextCommandValidator.isRequestExitLoop;

/**
 * @see duke.coronet.level.Increment_01_Test
 */
public class OrchestratorLevel01 extends AbstractOrchestrator {
    public static void main(String[] args) throws Exception {
        new OrchestratorLevel01(null, null, new UxManager(System.out)).run();
    }
    public OrchestratorLevel01(TaskManager tm, FileResourceManager frm, UxManager uxMgr) {
        super(tm, frm, uxMgr);
        uxMgr.setUxCommandFactory(new UxCommandFactory() {
            @Override
            public Command executeTextCommand(String text, TaskManager taskManager, FileResourceManager frm) {
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
    }
    @Override
    public void run() throws Exception {
        this.printEntryMessage();
        this.inputLoop();
        this.printTerminateMessage();
    }
}