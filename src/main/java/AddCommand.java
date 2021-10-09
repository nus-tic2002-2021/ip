public class AddCommand extends Command{
    protected String add;
    public AddCommand(String action, String add){
        super(action);
        setAdd(add);
    }
    public void setAdd(String add) {
        this.add = add;
    }
}
