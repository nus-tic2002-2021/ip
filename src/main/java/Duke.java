import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static String[] commandList = new String[100];
    private static int commandCount = 0;

    public static void addCommand(String command){
        commandList[commandCount] = command;
        commandCount++;
    }
    public static void reply(String command){
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
            System.out.println("    "+numbering+". "+commandList[i]);
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
                addCommand(command);
                command = in.nextLine();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
