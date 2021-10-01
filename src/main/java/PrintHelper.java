public class PrintHelper {
    public void separator(){
        System.out.println("    ____________________________________________________________");
    }

    public void taskDoneFeedback(){
        separator();
        System.out.println("     Nice! I've marked this task as done:");
    }
    public void taskAddFeedback(){
        separator();
        System.out.println("     Got it. I've added this task:");
    }
    public void taskFullListFeedback(int taskCount, Task[] taskList){
        int numbering = 1;
        separator();
        System.out.println("     Here are the tasks in your list:");
        for(int i=0;i<taskCount;i++){
            System.out.println("    "+numbering+"."+taskList[i].toString());
            numbering++;
        }
        separator();
    }
    public void welcomeFeedback(){
        separator();
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        separator();
    }
    public void exitFeedback(){
        separator();
        System.out.println("    Bye. Hope to see you again soon!");
        separator();
    }
    public void emptyDescription(String taskType){
        separator();
        System.out.printf("     ☹ OOPS!!! The description of a %s cannot be empty.\n", taskType);
        separator();
    }
    public void emptyTaskList(){
        separator();
        System.out.println("     ☹ OOPS!!! The list is empty ^_^");
        separator();
    }
    public void unknownSyntax(String message){
        separator();
        System.out.printf("     ☹ OOPS!!! I'm sorry, but I don't know what [%s] means :-(\n", message);
        separator();
    }
    public void taskNotFound(String message){
        separator();
        System.out.printf("     ☹ OOPS!!! Please key in by Task ID instead of [%s]\n", message);
        separator();
    }
    public void noTimeConcept(){
        separator();
        System.out.println("     ☹ HEY!!! Please give a due date for deadlines and events. Let's have some sense of time >_<|||");
        separator();
    }
}
