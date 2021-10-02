public class Deadline extends Task{
    String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
        type = Types.DEADLINE;
    }

    public void showTask(){
        String mark = isDone ? "X" : " ";
        System.out.println("[D][" + mark + "] " + description + " (by: " + by + ")");
    }

}