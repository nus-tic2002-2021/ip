package duke.tasklist;

public class Deadline extends Task {
    public Deadline(String description){
        super(description);
        this.type = "D";
        this.isDone = false;
    }

    @Override
    public String getDescription() {return description.substring(9,description.indexOf('/'));}

    public  String getDatetime() {
        return description.substring(description.indexOf('/')+3);
    }

    public String printTask (){
        return "[" + getType() +"]" + "[" + getStatusIcon() +"] " + getDescription() + "(by:" + getDatetime() + ")";
    }

}
