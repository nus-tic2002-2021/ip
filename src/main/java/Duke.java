import java.util.Scanner;
public class Duke {

    private static String lineBreak = "\t________________________________________";

    public static void initialize (){
        Scanner in = new Scanner(System.in);
        String inputLine = "Hello! I'm Duke\n\tWhat can I do for you?";
        String[] inputList = new String[100];
        while (!inputLine.equals("bye")) {
            printResponse(inputLine);
            inputLine = in.nextLine();
        }
        printResponse("Bye. Hope to see you again soon!");
    }
    public static void printResponse(String inputMsg) {
        System.out.println(lineBreak);
        System.out.println("\t" + inputMsg);
        System.out.println(lineBreak);
    }



    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        initialize();

    }
}
