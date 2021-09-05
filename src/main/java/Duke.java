import java.util.Scanner;

public class Duke {

    public static Task[] tasks = new Task[100];
    private static int tasksCount = 0;
    //Declare the breakline as a class-level variable
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
        String status = "";
        line = in.nextLine();

        while(!line.equalsIgnoreCase("bye")){
            //extract description from the string line
            String[] taskSplit = line.split(" ");
            if(taskSplit[0].equals("todo") || taskSplit[0].equals("deadline") || taskSplit[0].equals("event")){
                taskSplit[0]="";
            }
            String text = String.join(" ", taskSplit);
            Task t = new Task(text);
            //TODO ask teacher how to connect child class to super class

            if(line.contains("list")){
                printList();
            }
            else if(line.contains("done")){
                String[] userKeyIn = t.description.split(" ");
                //TODO write a class or function for done
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

            else if(line.contains("todo")){
                tasks[tasksCount] = new Todo(text);
                tasks[tasksCount].setTaskStatus("T");
//                System.out.println(breakLine);
                System.out.println(tasks[tasksCount].toString());
                addTask(t);
                System.out.println("Now you have "+tasksCount+" tasks in the list.");
            }

            else if(line.contains("deadline")){
                //get the index of /
                String[] deadlineAndTime = text.split("/by");
                tasks[tasksCount] = new Deadline(deadlineAndTime[0],deadlineAndTime[1]);
                tasks[tasksCount].setTaskStatus("D");

                System.out.println(tasks[tasksCount].toString());
                addTask(t);
                System.out.println("Now you have "+tasksCount+" tasks in the list.");


            }
            else if(line.contains("event")){
                String[] eventAndTime = text.split("/at");
                tasks[tasksCount] = new Event(eventAndTime[0],eventAndTime[1]);
                tasks[tasksCount].setTaskStatus("E");

                System.out.println(tasks[tasksCount].toString());
                addTask(t);
                System.out.println("Now you have "+tasksCount+" tasks in the list.");

            }

            line = in.nextLine();
        }

        System.out.println(
                breakLine + "Bye. Hope to see you again soon!\n" + breakLine);
    }

    public static void addTask(Task t){
//        tasks[tasksCount] = t;
        tasksCount++;
    }

    public static void printList(){
        //println is print with extra \n, print is purely printout.
        System.out.print(breakLine);
        System.out.println("Here are the tasks in your list:");
        //print out all items in list
        for(int i = 0; i< tasksCount;i++){
            System.out.println(i+1 + "."+"["+tasks[i].getTaskStatus()+"]" +"["+tasks[i].getStatusIcon()+"]"+tasks[i].getDescription());
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
