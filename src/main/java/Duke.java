import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;
    private static PrintHelper printer = new PrintHelper();

    public static void addCommand(String command){
        String[] toWords = command.split(" ", 2);
        String keyword = toWords[0];
        String cmd = toWords[1];
        //System.out.println("keyword: "+keyWord[0]);
        switch(keyword){
            case "todo":
                taskList[taskCount] = new ToDos(cmd, cmd);
                break;
            case "deadline":
                String[] dl = cmd.split("/by", 2);
                //System.out.println("cmd: "+dl[0]);
                //System.out.println("by: "+dl[1]);
                taskList[taskCount] = new Deadline(dl[0],dl[1]);
                break;
            case "events":
                String[] dt = cmd.split("/at", 2);
                //System.out.println("cmd: "+dt[0]);
                //System.out.println("at: "+dt[1]);
                taskList[taskCount] = new Events(dt[0], dt[1]);
                break;
            default:
                taskList[taskCount] = new Task(command);
                break;
        }
        /*if(keyword.equals("")){
            taskList[taskCount] = new ToDos(cmd, cmd);
        }
        else if(keyword.equals("deadline")){
            String[] dl = cmd.split("/by", 2);
            //System.out.println("cmd: "+dl[0]);
            //System.out.println("by: "+dl[1]);
            taskList[taskCount] = new Deadline(dl[0],dl[1]);
        }
        else if(keyword .equals("event")){
            String[] dt = cmd.split("/at", 2);
            //System.out.println("cmd: "+dt[0]);
            //System.out.println("at: "+dt[1]);
            taskList[taskCount] = new Events(dt[0], dt[1]);
        }
        else{
            //System.out.println("Keyword not found");
            taskList[taskCount] = new Task(command);
        }*/
        taskCount++;
    }

    public static void reply(String command){
        if(command.contains("done")){
            String[] doneCmd = command.split(" ");
            taskList[Integer.parseInt(doneCmd[1])-1].setDone();
            printer.taskDoneFeedback();
            System.out.println("     "+ taskList[Integer.parseInt(doneCmd[1])-1].toString());
            printer.separator();
            return;
        }
        printer.taskAddFeedback();
        System.out.println("     "+ taskList[taskCount-1].toString());
        System.out.println("     Now you have "+ taskCount + " tasks in the list.");
        printer.separator();
    }

    public static void printAll(){
        if(taskCount==0){
            printer.emptyList();
            return;
        }
        printer.taskFullListFeedback(taskCount,taskList);
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
        printer.welcomeFeedback();
        command = in.nextLine();

        while(!end){
            if(command.equals("bye")){
                end = true;
            }
            else if(command.equals("list")){
                printAll();
                command = in.nextLine();
            }
            else if(command.contains("done")){
                reply(command);
                command = in.nextLine();
            }
            else{
                addCommand(command);
                reply(command);
                command = in.nextLine();
            }
        }
        printer.exitFeedback();
    }
}
