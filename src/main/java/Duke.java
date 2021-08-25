import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("________________________________");
        System.out.println("Hello, I'm Duke!" );
        System.out.println("What can I do for you?");
        System.out.println("________________________________");

        while (!exit){
            String inputStr = sc.nextLine();

            if (inputStr.equalsIgnoreCase("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                exit = true;
            }
            else{
                System.out.println(inputStr);
            }
        }
    }
}
