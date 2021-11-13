package dukeMain.tasks;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }
    public ToDos(boolean done, String description){
        super(description,done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toStringSaveTask(String separator ){
        separator = " " + separator + " ";
        return "T" +separator+ super.toStringSaveTask(separator);
    }
}