package Duke.ui;

/**
 * handle msg output
 */
public class ReturnMessages {
    public void separator(){
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Feedback when marking a task done
     */
    public void taskDoneFeedback(){
        separator();
        System.out.println("     Nice! I've marked this task as done:");
    }

    /**
     * Feedback when adding a task
     */
    public void taskAddFeedback(){
        separator();
        System.out.println("     Got it. I've added this task:");
    }

    /**
     * Feedback on program startup
     */
    public void welcomeFeedback(){
        separator();
        System.out.println("    Hello! I'm Duke");
        System.out.println("    Here are my functionality....");
        System.out.println("    Check Existing Tasks:    list");
        System.out.println("    Mark a Task done:        done {taskID}");
        System.out.println("    Delete a Task:           delete {taskID}");
        System.out.println("    Add a Todo Task:         todo {action}");
        System.out.println("    Save Current State:      save");
        System.out.println("    Add a Event Task:        event xxxx /at {date by YYYY/MM/DD} {startTime}AM/PM-{endTime}AM/PM");
        System.out.println("    Add a Deadline Task:     deadline XXXX /by {date by YYYY/MM/DD} {Time}AM/PM");
        System.out.println("    End the program:         bye");
        separator();
    }

    /**
     * Feedback on program exit
     */
    public void exitFeedback(){
        separator();
        System.out.println("    Bye. Hope to see you again soon!");
        separator();
    }

    /**
     * Feedback when loading file.
     */
    public void loadingFileFeedback(){
        separator();
        System.out.println("     Loading File...");
        separator();
    }

    /**
     * Feedback on file load success.
     */
    public void loadSuccessFeedback(){
        separator();
        System.out.println("     File Loaded Successfully, use command 'list' to view...");
        separator();
    }

    /**
     * Feedback on file save
     */
    public void saveSuccessFeedback(){
        separator();
        System.out.println("     Successfully wrote to the file.");
        separator();
    }

    /**
     * Feedback on file create
     */
    public void createSuccessFeedback(String name){
        separator();
        System.out.println("File created: " + name);
        separator();
    }

    /**
     * Feedback message on emptyDescription Exception thrown
     * @param taskType The type of task (To Do, Event, Deadline)
     */
    public void exception_feedback_emptyDescription(String taskType){
        separator();
        System.out.printf("     OOPS!!! The description of a %s cannot be empty.\n", taskType);
        separator();
    }

    /**
     * Feedback message on emptyTaskList Exception thrown
     */
    public void exception_feedback_emptyTaskList(){
        separator();
        System.out.println("     OOPS!!! The list is empty ^_^");
        separator();
    }

    /**
     * Feedback message on unknownSyntax Exception thrown
     * @param message - user input
     */
    public void exception_feedback_unknownSyntax(String message){
        separator();
        System.out.printf("     OOPS!!! I'm sorry, but I don't know what [%s] means :-(\n", message);
        separator();
    }

    /**
     * Feedback message on taskNotfound Exception thrown
     * @param message - the task description
     */
    public void exception_feedback_taskNotFound(String message){
        separator();
        System.out.printf("     OOPS!!! Please key in by Tasks.Task ID instead of [%s]\n", message);
        separator();
    }

    /**
     * Feedback message on noTimeConcept Exception thrown
     */
    public void exception_feedback_noTimeConcept(){
        separator();
        System.out.println("     HEY!!! Please give a due date for deadlines and events or the syntax could be wrong");
        separator();
    }

    /**
     * Feedback message on Time Parse Exception thrown
     */
    public void exception_feedback_timeParse(String message){
        separator();
        System.out.printf("     There seems to have problem with your time format [%s]\n", message);
        separator();
    }

    /**
     * Feedback message on Saving Exception thrown
     */
    public void exception_feedback_cantSave(){
        separator();
        System.out.println("     OOPS!!! Exception caught while saving >_<|||");
        separator();
    }

    /**
     * Feedback message on Loading Exception thrown
     */
    public void exception_feedback_loadingError(){
        separator();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("     OOPS!!! Something went wrong while loading");
        separator();
    }

    public void exception_feedback_unknownError(){
        separator();
        System.out.println("Sorry, An unknown error occurred");
        separator();
    }
}
