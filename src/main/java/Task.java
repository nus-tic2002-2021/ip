public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override  /*testing override*/
    public String toString() {
        String box = "[ ] ";
        if (isDone){
            box = "[X] ";
        }
        return (box + description);
    }
}
