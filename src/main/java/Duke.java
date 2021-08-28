import java.util.Scanner;
public class Duke {

    private static String lineBreak = "\t________________________________________";
    private static List inputList;

    public static void initialize (){
        Scanner in = new Scanner(System.in);
        String inputLine = "";
        inputList = new List();

        printIntro();

        while (!inputLine.equals("bye")) {
            inputLine = in.nextLine();
            printResponse(inputLine);
        }
    }
    public static void printIntro(){
        System.out.println(lineBreak);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println(lineBreak);
    }
    public static void printResponse(String inputMsg) {
        System.out.println(lineBreak);
        if (inputMsg.equals("bye")){
            System.out.println("\tBye. Hope to see you again soon!");
        }
        else if(inputMsg.equals("list")){
            inputList.printList();
        }
        else{
            inputList.storeList(inputMsg);
            System.out.println("\tadded: " + inputMsg);
        }
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
