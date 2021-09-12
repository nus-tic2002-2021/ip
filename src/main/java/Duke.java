import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void printLine(){
        System.out.println("    --------------------------------");
    }

    public static void dukeGreet(){
        printLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?\n");
        printLine();
    }

    public static void dukeEcho(){
        String req;
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        while(!(req = sc.nextLine()).equals("bye")){
            System.out.println(req + "\n");
            printLine();
            if(req.equals("list")){
                System.out.println("     Here are the tasks in your list:");
                for(int i = 0; i < list.size(); i++){
                    System.out.println("     " + (i + 1) + "." + list.get(i));
                }
            }
            else if(req.substring(0, 4).equals("done")){
                System.out.println("     Nice! I've marked this task as done:");
                int idx = Integer.parseInt(req.trim().split(" ")[1]) - 1;
                list.set(idx, "[X] " + list.get(idx).substring(4));
                System.out.println("       " + list.get(idx));
            }
            else{
                System.out.println("     added: " + req);
                list.add("[ ] " + req);
            }
            System.out.println();
            printLine();

        }
        System.out.println(req + "\n");
        printLine();
        System.out.println("     Bye.Hope to see you again soon!\n");
        printLine();

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        dukeGreet();
        dukeEcho();

    }
}