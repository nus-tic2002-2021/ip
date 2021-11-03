package duke.tasklist;

public class Event extends Task{
    public Event(String description){
        super(description);
        this.type = "E";
        this.isDone = false;
    }

    @Override
    public String getDescription() {return description.substring(6,description.indexOf('/'));}

    public String getDatetime() {
        return description.substring(description.indexOf('/')+3);
    }


    @Override
    public String printTask (){
        //print out every element in the array
        return "[" + getType() +"]" + "[" + getStatusIcon() +"] " + getDescription() + "(by:" + getDatetime() + ")";
    }
}
