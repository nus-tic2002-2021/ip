public class Todo extends Task{

    public Todo(String description){
        super(description);
        type = Types.TODO;
    }

    public void showTask(){
        String mark = isDone ? "X" : " ";
        System.out.println("[T][" + mark + "] " + description);
    }

}