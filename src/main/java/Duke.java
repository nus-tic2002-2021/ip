import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke ");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String line = "";
        String[] lists = new String[100];
        int counter=0;
        while(true) {

            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if(line.equalsIgnoreCase("bye"))
                break;
            if(line.equalsIgnoreCase("list")){
                System.out.println("____________________________________________________________");
                for (int i = 0; i < counter; i++) {
                    System.out.println(i+1 + ". " + lists[i]);
                }
                System.out.println("____________________________________________________________");
            }else {
                System.out.println("____________________________________________________________");
                lists[counter]=line;
                counter++;
                System.out.println("added: " + line);
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }


}
