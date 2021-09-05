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

            if(t.description.contains("list")){
                printList();
            }
            else if(t.description.contains("done")){
                String[] userKeyIn = t.description.split(" ");
                //check the str behind the done is a digit
                try
                {
                    Integer.parseInt(userKeyIn[1]);
                    int taskIndex = Integer.parseInt(userKeyIn[1])-1;

                    tasks[taskIndex].markAsDone();
                    printTaskDone(tasks[taskIndex]);
                }
                //check the input is integer
                catch (NumberFormatException e)
                {
                    System.out.println(line + " is not a valid integer");
                }
                //check the input is not 0, and it is a real task id
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Enter a correct task number");
                }
                //check the input is not 0, is not exceed the total tasks index
                catch(NullPointerException e){
                    System.out.println("There are only "+tasksCount+" tasks, please enter the correct task number");
                }



            }
            else if (!line.equals("")){
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
//        Task t = new Task("read book");
//        t.markAsDone();
//        updateList(t.getDescription());
//        printList();
    }
}
