public class ExitCommand extends Command{
    public ExitCommand(String action){
        super(action);
    }
    public void execute(List tasks, Storage storage){
        tasks.saveList();
        //tasks.getSave().listIterator();
        storage.saveFile(tasks.getSave());
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
