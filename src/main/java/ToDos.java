public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
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