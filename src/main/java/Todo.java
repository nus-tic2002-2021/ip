public class Todo extends Task{

    public Todo(String description){
        super(description);
        taskType = 'T';
    }

    public void showTask(){
        String mark = isDone ? "X" : " ";
        System.out.println("[" + taskType + "][" + mark + "] " + description);
    }

}