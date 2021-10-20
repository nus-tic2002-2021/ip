package Duke.ui;

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
        System.out.println("    Hello! I'm Duke.Duke");
        System.out.println("    What can I do for you?");
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
        System.out.println("     HEY!!! Please give a due date for deadlines and events. Let's have some sense of time >_<|||");
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
}
