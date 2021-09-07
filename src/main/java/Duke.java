import java.util.Scanner;

public class Duke {
    public static void printTasks(String[] tasks, int numTask) {
        for (int i=0; i< numTask; i++){
            String task = tasks[i];
            System.out.println(i+1+". "+task);
        }
    }

    public static void main(String[] args) {
        //print greeting messages
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "Hey this is Blanc, welcome to your universe\n"
                + "I will provide everything you need:))\n"
                + "Please type any command to start:";
        System.out.println(greeting);
        //take user input
        Scanner in = new Scanner(System.in);
        String command = "";
        //initiate an Array to store tasks, assume numTask <= 100
        String[] tasks = new String[100];
        int numTask = 0;
        while (true){
            command = in.nextLine();
            if (command.equals("bye")){
                break;
            }
            //add task to the list
            if (!command.equals("list")){
                tasks[numTask] = command;
                System.out.println("added "+ command);
                numTask ++;
            }
            //print out the list
            else{printTasks(tasks, numTask);}
        }
        System.out.println("Bye. Have a nice day!");
    }
}
