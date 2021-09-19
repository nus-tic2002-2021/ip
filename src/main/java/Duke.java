//import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String text;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];

        System.out.println("Yo, I'm Alice!\nHow can i help you?");
        System.out.println("=======================================================");

        //takes in user input
        text = in.nextLine();

        //use while loop to program running
        while(!text.equalsIgnoreCase("bye")){
            //print the current list by calling printList method, if user inputs "list"
            if (text.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i=0; i < Task.task_count; i++) {
                    System.out.println(i+1 + "." + tasks[i].printList());
                }
                System.out.println("=======================================================");
            } else if(text.contains("done")){ //
                String[] word = text.split(" ");
                int i = Integer.parseInt(word[1]) - 1;
                tasks[i].setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("    [" + tasks[i].getStatusIcon() +"] " + tasks[i].getDescription());
                System.out.println("=======================================================");
            } else { //every other words will be added to the list by calling addToList method
                Task.addToList(text, tasks);
            }
            text = in.nextLine();
        }

        System.out.println("Bye, i will miss you.");
        System.out.println("=========================END===========================");
        System.exit(0);
    }
}
