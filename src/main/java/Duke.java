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
                for(int i = 0; i < list.size(); i++){
                    System.out.println("     " + (i + 1) + ". " + list.get(i));
                }
                System.out.println();
            }
            else{
                System.out.println("     added: " + req + "\n");
                list.add(req);
            }
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