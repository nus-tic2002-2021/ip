public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }

    public Deadline(String description, String by, Boolean isDone) {
        super(description);
        setBy(by);
        this.isDone = isDone;
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

    public String getTask(){
        return "D";
    }
    public String getDescription(){
        return description;
    }
    public String getBy(){
        return by;
    }

    public String getSave(){
        String s = getTask() + " | " +  getDone() + " | " + getDescription() + " | " + getBy();
        return s;
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