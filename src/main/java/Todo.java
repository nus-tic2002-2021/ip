public class Todo extends Task{
    protected boolean isDone;

    public Todo(String description){
        super(description);
        isDone = false;
    }

    public void setDone(){
        this.isDone = true;
        System.out.println("\tNice! I've marked this task as done:\n" +
                            "\t  [T][X] " + description);
    }

    public void print(){
        if (isDone) {
            System.out.println("\t  [T][X] " + description);
        }
        else {
            System.out.println("\t  [T][ ] " + description);
        }
    }

    @Override
    public String toString() {
        String box = "[T][ ] ";
        if (isDone){
            box = "[T][X] ";
        }
        return (box + description);
    }
}
