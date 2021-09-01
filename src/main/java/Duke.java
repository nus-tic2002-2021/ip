import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static Task[] taskList = new Task[100];
    private static int commandCount = 0;

    public static void addCommand(String command){
        taskList[commandCount] = new Task(command);
        commandCount++;
    }
    public static void reply(String command){
        if(command.contains("done")){
            String[] doneCmd = command.split(" ");
            taskList[Integer.parseInt(doneCmd[1])-1].setDone();
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("     "+"[X] "+taskList[Integer.parseInt(doneCmd[1])-1].getTaskInfo());
            System.out.println("    ____________________________________________________________");
            return;
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: "+command);
        System.out.println("    ____________________________________________________________");

    }

    public static void printAll(){
        if(commandCount==0){
            System.out.println("    ____________________________________________________________");
            System.out.println("    Nothing in list yet...");
            System.out.println("    ____________________________________________________________");
            return;
        }
        int numbering = 1;
        System.out.println("    ____________________________________________________________");
        for(int i=0;i<commandCount;i++){
            System.out.println("    "+numbering+". ["+taskList[i].getStatusIcon()+"] "+taskList[i].getTaskInfo());
            numbering++;
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        boolean end = false;
        String command;
        Scanner in = new Scanner(System.in);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        command = in.nextLine();
        while(!end){
            if(command.equals("bye")){
                end = true;
            }
            else if(command.equals("list")){
                printAll();
                command = in.nextLine();
            }
            else{
                reply(command);
                if(!command.contains("done")){
                    addCommand(command);
                }
                command = in.nextLine();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
