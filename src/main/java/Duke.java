import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String text;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int task_count = 0;

        System.out.println("Yo, I'm Alice!\nHow can i help you?");
        System.out.println("=======================================================");

        //takes in user input
        text = in.nextLine();
        tasks[task_count] = new Task(text);

        //use while loop to program running
        while(text != null){
            //print the current list by calling printList method, if user inputs "list"
            if (text.equalsIgnoreCase("list")) {
                Task.printList(tasks);
                System.out.println("=======================================================");
            } else if(text.contains("done")){
                String[] word = text.split(" ");
                int i = Integer.parseInt(word[1]) - 1;
                tasks[i].setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + tasks[i].getStatusIcon() +"] " + tasks[i].getDescription());
                System.out.println("=======================================================");
            } else {
                //every other words will be added to the list by calling addToList method
                Task.addToList(text, tasks);
            }
            text = in.nextLine();
        }
    }
}
