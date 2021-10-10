public class AddCommand extends Command{
    protected String add;
    public AddCommand(String action, String add){
        super(action);
        setAdd(add);
    }
    public void setAdd(String add) {
        this.add = add;
    }

    public void execute(List tasks,Storage storage, UI ui) {
        try {
            tasks.addTask(action, add);
        } catch (InvalidFormatException e) {
            System.out.println("\t" + e.getMessage());
        }

    }
}
