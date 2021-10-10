public class ExitCommand extends Command{
    public ExitCommand(String action){
        super(action);
    }
    public void execute(List tasks, Storage storage,UI ui){
        tasks.saveList();
        ui.printExit();
        storage.saveFile(tasks.getSave());
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
