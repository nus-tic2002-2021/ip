package task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        super.type("T");
        super.isDone = false;
    }

    @Override
    public String printTask() {
        return super.printTask() ;
    }

}
