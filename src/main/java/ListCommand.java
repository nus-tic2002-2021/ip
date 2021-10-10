public class ListCommand extends Command {
    public ListCommand(String action) {
        super(action);
    }
    public void execute(List tasks, Storage storage,UI ui){
        tasks.printList();
    }
}