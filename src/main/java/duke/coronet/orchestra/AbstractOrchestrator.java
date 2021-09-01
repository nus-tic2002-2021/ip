package duke.coronet.orchestra;

import duke.coronet.manager.FileResourceManager;
import duke.coronet.manager.TaskManager;
import duke.coronet.manager.UxManager;

/**
 * An orchestrator has member managers.
 * Orchestrators inject dependency members to dependant member.
 * A subclass object modifies behaviour to comply with assignment requirements
 */
public abstract class AbstractOrchestrator {
    private TaskManager _taskManager;
    private FileResourceManager _frMgr;
    private UxManager _uxMgr;
    public AbstractOrchestrator(TaskManager tm, FileResourceManager frm, UxManager uxMgr) {
        this.setTaskManager(tm);
        this.setFileResourceManager(frm);
        this.setUxManager(uxMgr);
    }
    private AbstractOrchestrator() {
    }
    private void setTaskManager(TaskManager tm) {
        this._taskManager = tm;
    }
    private TaskManager getTaskManager() {
        return this._taskManager;
    }
    private void setFileResourceManager(FileResourceManager frm) {
        this._frMgr = frm;
    }
    private FileResourceManager getFileResourceManager() {
        return this._frMgr;
    }
    private void setUxManager(UxManager uxMgr) {
        this._uxMgr = uxMgr;
    }
    private UxManager getUxManager() {
        return this._uxMgr;
    }
    public void printEntryMessage() {
        this.getUxManager().printEntryMessage();
    }
    public void printExitMessage() {
        this.getUxManager().printTerminateMessage();
    }
    public abstract void run() throws Exception;
    public void inputLoop() throws Exception {
        this.getUxManager().userInputLoop(this.getTaskManager(), this.getFileResourceManager());
    }
}
