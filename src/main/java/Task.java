public class Task {

    private String description;
    private boolean isDone;
    // T for todo, E for Events, D for deadlines
    private String acronym;

    public Task(){}

    public Task(String des, String acr){
        description = des;
        acronym = acr;
        isDone = false;
    }

    public void setDone(boolean done){
        isDone = done;
    }

    public String getDate() { return "";  } // not using but implemented here just for overriding purposes
    public String getClock() { return "";  } // not using but implemented here just for overriding purposes

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public String getDescription(){
        return description;
    }

    public String getAcronym() { return acronym; }
}
