abstract class Task {
    protected String description;

    public Task(String description) {
        setDescription(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract void setDone();
    public abstract void print();
}
