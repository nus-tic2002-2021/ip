package duke.task;

public class Todo extends Task{

    public Todo(String description){
        super(description);
        type = Types.TODO;
    }

    public Todo(boolean isDone, String description){
        super(description);
        this.isDone = isDone;
        type = Types.TODO;
    }

    public void showTask(){
        String mark = isDone ? "X" : " ";
        System.out.println("[T][" + mark + "] " + description);
    }

    public String toStoreString(){
        int state = isDone ? 1 : 0;
        return 'T' + " | " + state + " | " + description + System.lineSeparator();
    }
}
