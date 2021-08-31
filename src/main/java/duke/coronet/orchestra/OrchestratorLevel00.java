package duke.coronet.orchestra;

import duke.coronet.manager.FileResourceManager;
import duke.coronet.manager.TaskManager;
import duke.coronet.manager.UxManager;

/**
 * @see duke.coronet.level.Increment_00_Test
 */
public class OrchestratorLevel00 extends AbstractOrchestrator {


    public static void main(String[] args) {
        new OrchestratorLevel00(null, null, new UxManager(System.out)).run();
    }

    public OrchestratorLevel00(TaskManager tm, FileResourceManager frm, UxManager uxMgr) {
        super(tm, frm, uxMgr);
    }

    @Override
    public void run() {
        this.printEntryMessage();
        this.printExitMessage();
    }


}
