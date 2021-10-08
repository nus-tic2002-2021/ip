import java.util.Scanner;

public class Duke {
    private static String lineBreak = "\n------------------- \n";
    public static void main(String[] args) {
        String logo = "  _       _ _      _ _______________________________________ \n"
                    + " | |  _  | | |    | |  __  |        |  __  |  __  |___   ___|\n"
                    + " | | | | | | |----| | |__| |  ------| |__| | |__|/    | |    \n"
                    + " | |_| |_| | |----| | |  | |------  | |  | | |__|\\ ___| |___\n"
                    + " |_________|_|    |_|_|  |_|________|_|  |_|______|_________|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can do for you?");

        Scanner in;
        String line;
        while(true){
            in = new Scanner(System.in);
            line = in.nextLine();
            if(line.equalsIgnoreCase("bye")) {
                printThis("Bye. Hope to see you again soon!");
                break;
            }
            printThis(line);
        }
    }
    public static void printThis(String st){
        System.out.println(lineBreak +st+lineBreak);
    }
}
