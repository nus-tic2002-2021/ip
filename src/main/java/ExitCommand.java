public class ExitCommand extends Command{
    protected boolean isExit;
    public ExitCommand(String action){
        super(action);
        isExit = true;
    }
}
