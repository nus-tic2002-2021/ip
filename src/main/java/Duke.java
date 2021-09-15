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
        Task newlist = new Task();

        while(!userInput.trim().equalsIgnoreCase("bye")){
            newlist.addTask(userInput);
            if(userInput.trim().equalsIgnoreCase("list")){
                System.out.println(newlist.showList());
            }
//            System.out.println(horiLine + "\n" + buff + userInput.trim() + "\n" + horiLine); // echo function
            userInput = myObj.nextLine();

        }

        System.out.println(horiLine + "\n" + buff + valediction + "\n" + horiLine);
    }
}
