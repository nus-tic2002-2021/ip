public class Deadline extends Task {

    protected String by;
    protected String type;

    public Deadline(String description, String by) {
        //super allows us to grab from the super parent class task's description
        super(description);
        this.by = by;
        this.type = "D";
    }

    public void setBy(String by) {
        this.by = by;
    }
    public String getBy() {
        return by;
    }

    @Override
    public String getType() { return this.type; }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}



//Create (REWORK)
//------------------------------------
// edit the todo to be "todo borrow book"
//
// probably can do a enum for the days
//     Got it. I've added this task:
//       [E][ ] project meeting (at: Mon 2-4pm)
//     Now you have 7 tasks in the list.

// Delete (REWORK)
//-------------------------------------
// allow to delete by description regex

// Read (DONE)
// ------------------------------------
// allow to save into txt file harddisk (LV7)
// allow regex to search for current tasks

// Error handle
//-------------------------------------
// todo
// i see you want to make a todo, please add description

//Indiv feature - mass ops

//Indiv feature - welcome name