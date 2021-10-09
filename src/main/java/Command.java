abstract class Command {
    protected String action;

    public Command(String action){
        setAction(action);
    }

    public void setAction(String action) {
        this.action = action;
    }
}
