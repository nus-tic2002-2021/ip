package data;

public class Task {

    private String description;
    private boolean isDone;
    // T for todo, E for Events, D for deadlines
    private Acronym acronym;

    public Task(){}

    /**
     * Constructor for Task Class with 2 param to set for description and acronym
     * At the start of this method called task is set to false.
     * @param des is for description of the task
     * @param acr is for acronym of the task (T,E,D)
     */
    public Task(String des, Acronym acr){
        description = des;
        acronym = acr;
        isDone = false;
    }

    /**
     * Setter for isDone variable. Task mark as DONE
     * @param done is passed so that in future can implement for user to mark as undone if necessary
     */
    public void setDone(boolean done){
        isDone = done;
    }

    /**
     * Method to show whether the task is completed or not in the task list
     * @return a String type X for done and " " for not yet done
     */
    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    /**
     * Method to show the completeness of the task in the task list
     * @return return boolean status depending on isDone status
     */
    public boolean getDone(){
        return isDone;
    }

    /**
     * Method to show the description of the task in the task list
     * @return return String type description variable
     */
    public String getDescription(){
        return description;
    }

    /**
     * Method to get the acronym of the task
     * @return return Acronym type. Acronym is an Enumeration containing T, E, D
     */
    public Acronym getAcronym() { return acronym; }

    /**
     * Method to display date and time but here in Task class there are no date or time variable
     * this is just a empty method to be overridden by Class Event and DeadLine
     * @return "" return nothing
     */
    public String displayDateTime(){
        return "";
    }
}
