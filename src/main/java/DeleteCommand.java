public class DeleteCommand extends Command{
    protected String delete;
    public DeleteCommand(String action, String delete){
        super(action);
        setDelete(delete);
    }
    public void setDelete(String add) {
        this.delete = delete;
    }
}