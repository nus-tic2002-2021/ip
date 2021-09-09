import java.util.Scanner;

public class Duke {
    public static void printTasks(Task[] tasks, int numTask) {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i< numTask; i++){
            Task task = tasks[i];
            System.out.println(i+1+"."+task);
        }
    }

    public static void main(String[] args) {
        //print greeting messages
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */

        String greeting = "Hey this is Blanc, welcome to your universe\n"
                + "I will provide everything you need:))\n";
        String instruction = "Please follow the instructions below\n"
                + "\"list\": print a list of tasks\n"
                + "\"done +number\" (e.g. done 2): set the task status to done\n"
                + "task description: enter any task description to add the task\n"
                + "\"bye\": end the program\n"
                +"Please type any command to start: \n";
        System.out.println(greeting);
        System.out.println(instruction);
        //take user input
        Scanner in = new Scanner(System.in);
        //initiate an Array to store tasks, assume numTask <= 100
        Task[] tasks = new Task[100];
        Task.totalTasks = 0;
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                break;
            }
            //print out the list
            else if (command.equals("list")) {
                printTasks(tasks, Task.totalTasks);
            }
            //set status of a task to done
            else if (command.substring(0, 4).equals("done")) {
                int indexOfTask = Integer.parseInt(command.split(" ")[1]);
                //make sure the index is not out of the list
                try {
                    Task taskDone = tasks[indexOfTask - 1];
                    taskDone.setDone(true);
                    System.out.println("Nice! I've marked this task as done: \n"+taskDone);
                } catch (Exception e) {
                    System.out.println("Oops you don't have that many tasks, please enter a number <= "+Task.totalTasks);
                }
            }
            //add task to the list
            else {
                //check if it's a new task, if yes, add it into the list
                boolean redundant = false;
                Task newTask = new Task(command);
                for (Task task : tasks){
                    if (task != null && task.equals(newTask)){
                        redundant = true;
                    }
                }
                if (!redundant){
                    tasks[Task.totalTasks] = newTask;
                    System.out.println("added " + command);
                    Task.totalTasks++;
                }
                else {
                    System.out.println("The same task has been added previously, please enter another task");
                }
            }
        }
        System.out.println("Bye. Have a nice day!");

    }

}
