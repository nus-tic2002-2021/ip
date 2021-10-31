//import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String text;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];

        System.out.println("Hi. I'm A.L.I.C.E.\nHow can i help you?");
        System.out.println("=======================================================");

        //takes in user input
        text = in.nextLine();

        //use while loop to program running
        while(!text.equalsIgnoreCase("bye")){
            //print the current list by calling printList method, if user inputs "list"
            try {
                Task.tasks(text, tasks);
            } catch (DukeException e){
                System.err.println("\t" + e);
                System.out.println("=======================================================");
            } finally {
                text = in.nextLine();
            }
        }

        System.out.println("Bye, i will miss you.");
        System.out.println("=========================END===========================");
        System.exit(0);
    }
}
