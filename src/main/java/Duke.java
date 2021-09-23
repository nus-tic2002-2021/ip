import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Hello command
        System.out.println("Hello, I'm Duke\n" + "What can I do for you?");
        //read line
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("________________________________________________");
        while(true){
        line = in.nextLine();
        System.out.println(line +"\n________________________________________________");
            if(line.equals("bye")) {
                System.out.println("Bye! Thanks for visiting The Duke!");
                break;
            }
        }
    }
}
