import javax.xml.stream.events.DTD;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
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

    public static void response(){
        Scanner in = new Scanner(System.in);
        String line = "";
        String [] taskList = new String[2];

        ArrayList<String> actionKeyChecker = new ArrayList<String>(); // initialise the array
        actionKeyChecker.add("todo");
        actionKeyChecker.add("deadline");
        actionKeyChecker.add("event");
        actionKeyChecker.add("done");


        //check user input
        while (true){
            line = in.nextLine();
            String[] taskSplit = line.split(" ");

            String itemName = "";
            taskList[0] = taskSplit[0];

            try {
                if (actionKeyChecker.contains(taskList[0])){
                    itemName = line.substring(taskSplit[0].length()+1,line.length());
                }
                taskList[1] = itemName;
                responseParse(taskList);
            }catch (StringIndexOutOfBoundsException e){
                System.out.println(breakLine
                        +"☹ OOPS!!! The description of a "+taskList[0]+" cannot be empty.\n"
                        +breakLine);
            }


        }
    }

    public static void responseParse(String[] taskSplit){
        String actionKey = taskSplit[0];
        String actionValue = taskSplit[1];
        HashMap textCollection = new HashMap();


        Task t = new Task(actionValue);

        if(actionKey.equals("list")){
            printList();
        }
        else if(actionKey.equals("done")){
//            String[] userKeyIn = t.description.split(" ");
            //TODO write a class or function for done
            //check the str behind the done is a digit
            try
            {
                Integer.parseInt(actionValue);
                int taskIndex = Integer.parseInt(actionValue)-1;
                tasks[taskIndex].markAsDone();
                printTaskDone(tasks[taskIndex]);
            }
            //check the input is integer
            catch (NumberFormatException e)
            {
                System.out.println(taskSplit + " is not a valid integer");
            }

            //check the input is not 0, is not exceed the total tasks index
            catch(NullPointerException e){
                System.out.println("There are only "+tasksCount+" tasks, please enter the correct task number");
            }
            //check the 0 or negative
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("There are only "+tasksCount+" tasks, please enter the correct task number");
            }
        }

        else if(actionKey.equals("todo")){
            tasks[tasksCount] = new Todo(actionValue);
            tasks[tasksCount].setTaskStatus("T");
            System.out.println(tasks[tasksCount].toString());
            addTask(t);
            System.out.println("Now you have "+tasksCount+" tasks in the list.");
        }

        else if(actionKey.equals("deadline")){
            //get the index of /
            String[] deadlineAndTime = actionValue.split("/by");
            tasks[tasksCount] = new Deadline(deadlineAndTime[0],deadlineAndTime[1]);
            tasks[tasksCount].setTaskStatus("D");

            System.out.println(tasks[tasksCount].toString());
            addTask(t);
            System.out.println("Now you have "+tasksCount+" tasks in the list.");
        }

        else if(actionKey.equals("event")){
            String[] eventAndTime = actionValue.split("/at");
            tasks[tasksCount] = new Event(eventAndTime[0],eventAndTime[1]);
            tasks[tasksCount].setTaskStatus("E");

            System.out.println(tasks[tasksCount].toString());
            addTask(t);
            System.out.println("Now you have "+tasksCount+" tasks in the list.");

        }
        else if(actionKey.equals("bye")){
            System.out.println(
                breakLine + "Bye. Hope to see you again soon!\n" + breakLine);
            System.exit(0);
        }
        else{
            System.out.println(
                    breakLine + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + breakLine);
        }

    }

    public static void addTask(Task t){
        tasksCount++;
    }

    public static void printList(){
        String[] output = new String[10];
        //println is print with extra \n, print is purely printout.
        System.out.print(breakLine);
        System.out.println("Here are the tasks in your list:");
        //print out all items in list
        for(int i = 0; i< tasksCount;i++){
            output = tasks[i].toString().split("\n ");
            System.out.println(i+1 + "."+output[1]);
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
        response();
    }
}
