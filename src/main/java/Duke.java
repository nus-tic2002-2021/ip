import java.util.Arrays;
import java.util.Scanner;

public class Duke {
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
            else{
                System.out.println("    ____________________________________________________________");
                System.out.println("    "+command);
                System.out.println("    ____________________________________________________________");
                command = in.nextLine();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
