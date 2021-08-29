import java.util.Scanner;

public class Duke {

    public static void chatBot(String[] args) {
        String breakLine = "____________________________________________________________\n";

        System.out.println(
        breakLine
        + "Hello! I'm Duke\n"
        + "What can I do for you?\n"
        + breakLine);

        Scanner in = new Scanner(System.in);
        String line = "";
        line = in.nextLine();

        String[] list = new String[100];
        int count = 0;

        while(!line.equalsIgnoreCase("bye")){

            if(line.equals("list")){

                //println is print with extra \n, print is purely printout.
                System.out.print(breakLine);
                //print out all items in list
                for(int i = 0; i< count;i++){
                    System.out.println(i+1 + ". "+ list[i]);
                }
                System.out.println(breakLine);
            }
            else{
                //display the input
                System.out.println(
                        breakLine + "added: " + line +"\n" + breakLine);
                //append the items to list
                list[count] = line;
            }
            count++;
            line = in.nextLine();
        }
        
        System.out.println(
                breakLine + "Bye. Hope to see you again soon!\n" + breakLine);

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        chatBot(args);
    }
}
