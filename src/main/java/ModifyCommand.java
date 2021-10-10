public class ModifyCommand extends Command{
    protected String modify;
    public ModifyCommand(String action, String modify){
        super(action);
        setModify(modify);
    }
    public void setModify(String modify) {
        this.modify = modify;
    }
    public void execute(List tasks, Storage storage){
        try {
            tasks.taskDone(modify);
        } catch(NotFoundException e) {
            System.out.println("\tTask cannot be found.");
        } catch (NumberFormatException e){
            System.out.println("\tInvalid task number entry.");
        }
    }
}