import java.util.*;

public class Duke {
    public static void main(String[] args) {

        String greeting = "Hello! I'm Duke \n\tWhat can I do for you?";
        String valediction = "Bye. Hope to see you again soon!";
        String horiLine = "\t-----------------------------------------";
        String buff = "\t";
        System.out.println(horiLine + "\n" + buff + greeting + "\n" + horiLine);
        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();  // Read user input
        while(!userInput.trim().equalsIgnoreCase("bye")){
            System.out.println(horiLine + "\n" + buff + userInput.trim() + "\n" + horiLine);
            userInput = myObj.nextLine();
        }

        System.out.println(horiLine + "\n" + buff + valediction + "\n" + horiLine);
    }
}
