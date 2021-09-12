public class Deadline extends Task{
    String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
        taskType = 'D';
    }

    public void showTask(){
        String mark = isDone ? "X" : " ";
        System.out.println("[" + taskType + "][" + mark + "] " + description + " (by: " + by + ")");
    }

}