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
    public void emptyList(){
        separator();
        System.out.println("    Nothing in list yet...");
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
}
