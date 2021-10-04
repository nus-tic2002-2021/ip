public class Deadlines extends Task  {
    protected String by;

    public Deadlines(String description, boolean isDone, String by) {
        super(description, isDone);
        this.description = description;
        this.isDone = isDone;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public Task complete() {
        return new Deadlines(this.getName(),true,by);
    }

    @Override
    public Task setName(String newName) {
        return new Deadlines(newName, isDone,by);
    }
}


