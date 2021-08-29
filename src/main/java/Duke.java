import java.util.Scanner;

public class Duke {

    public static Task[] tasks = new Task[100];
    private static int tasksCount = 0;
    public static String breakLine = "____________________________________________________________\n";


    public static void greeting(){
        System.out.println(
        breakLine
        + "Hello! I'm Duke\n"
        + "What can I do for you?\n"
        + breakLine);
    }

    public static void collectUserInput(){
        Scanner in = new Scanner(System.in);
        String line = "";
        line = in.nextLine();

        while(!line.equalsIgnoreCase("bye")){
            Task t = new Task(line);

            if(line.equals("list")){
                printList();
            }

            else{
                //display the input
//                System.out.println(
//                        breakLine + "added: " + line +"\n" + breakLine);
                //append the items to list
                addTask(t);
            }
            line = in.nextLine();
        }

        System.out.println(
                breakLine + "Bye. Hope to see you again soon!\n" + breakLine);
    }

    public static void addTask(Task t){
        tasks[tasksCount] = t;
        tasksCount++;
    }

    public static void printList(){
        //println is print with extra \n, print is purely printout.
        System.out.print(breakLine);
        System.out.println("Here are the tasks in your list:");
        //print out all items in list
        for(int i = 0; i< tasksCount;i++){
            System.out.println(i+1 + ". "+ " ["+tasks[i].getStatusIcon()+"] "+tasks[i].getDescription());
        }
        System.out.println(breakLine);
    }

    public static void updateList(String taskIsDone){
        for(int i = 0; i< tasksCount;i++){
            if (tasks[i].getDescription().contains(taskIsDone)) {
                tasks[i].markAsDone();
            }
        }
    }

    public static void printTaskDone(Task t){
        System.out.println(breakLine+"Nice! I've marked this task as done: \n" +
                " ["+t.getStatusIcon()+"] " + t.getDescription() + "\n"+breakLine);
    }

    public static void main(String[] args) {
        greeting();
        collectUserInput();
        Task t = new Task("read book");
        t.markAsDone();
        updateList(t.getDescription());
        printList();
        printTaskDone(t);
    }
}
