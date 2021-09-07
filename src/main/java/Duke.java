import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //print greeting messages
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "Hey this is Blanc, welcome to your universe\n"
                + "I will provide everything you need:))\n"
                + "Please type any command to start:";
        System.out.println(greeting);
        //take user input
        Scanner in = new Scanner(System.in);
        String command = "";
        while (!command.equals("bye")){
            command = in.nextLine();
            System.out.println(command);
        }
    }
}
