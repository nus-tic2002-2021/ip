public class Deadline extends Todo{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }
    public void setDone(){
        this.isDone = true;
        System.out.println("\tNice! I've marked this task as done:\n" +
                "\t  [D][X] " + description + "(by: " + by + ")");
    }
    public void print(){
        if (isDone) {
            System.out.println("\t  [D][X] " + description + "(by: " + by + ")");
        }
        else {
            System.out.println("\t  [D][ ] " + description + "(by: " + by + ")");
        }

    }
    public void setBy(String by)
    {
        this.by = by;
    }
    @Override
    public String toString() {
        String box = "[D][ ] ";
        if (isDone){
            box = "[D][X] ";
        }
        return (box + description + "(by: " + by + ")");
    }
}