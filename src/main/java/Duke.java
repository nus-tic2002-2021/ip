import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<Task>();

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

    public static void getResponse(){
        Scanner in = new Scanner(System.in);
        String line = "";
        String taskStatus = "";
        String taskDescription = "";

        //check user input
        while (true){
            line = in.nextLine();
            //remove the whitespace from end
            line = line.stripTrailing();
            String[] taskSplit = line.split(" ",2);    //taskSplit[0] -> setTaskStatus

            //check the input and make sure it's not bye
            try{
                taskStatus = taskSplit[0].toUpperCase();
                if(line.contains("bye")){
                    System.out.println(breakLine
                                        + "Bye. Hope to see you again soon!\n" + breakLine);
                    System.exit(0);
                }

                else if (line.contains("list")){
                    printList();
                }

                else if(!contains(taskStatus)){
                    throw new IllegalArgumentException("Invalid argument");
                }

                else {
                    taskDescription = taskSplit[1].stripLeading();
                    responseParse(taskStatus,taskDescription);
                }

            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println(breakLine
                                    +"☹ OOPS!!! The description of a "+taskStatus+" cannot be empty.\n"
                                    +breakLine);
            }
            catch (IllegalArgumentException e){
                System.out.println(breakLine
                        + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + breakLine);
            }
        }
    }

    public static void responseParse(String taskStatus, String taskDescribe){
        //parse the Status of task
        Status status = Status.valueOf(taskStatus);
        String describe = taskDescribe;
        int taskIndex;
        try{
            switch (status){
                case TODO:
                    Todo todo = new Todo(describe);
                    todo.setTaskStatus("T");
                    tasks.add(todo);
                    System.out.println(breakLine+todo.toString());
                    break;
                case DEADLINE:
                    String[] deadlineAndTime = describe.split("/by");
                    Deadline deadline = new Deadline(deadlineAndTime[0],deadlineAndTime[1]);
                    deadline.setTaskStatus("D");
                    tasks.add(deadline);
                    System.out.println(breakLine+deadline.toString());
                    break;
                case EVENT:
                    String[] eventAndTime = describe.split("/at");
                    Event event = new Event(eventAndTime[0],eventAndTime[1]);
                    event.setTaskStatus("E");
                    tasks.add(event);
                    System.out.println(breakLine+event.toString());
                    break;
                case DELETE:
                    taskIndex = Integer.parseInt(describe)-1;
                    tasks.get(taskIndex).markAsDone();
                    System.out.println(breakLine+"Noted. I've removed this task: \n" +
                            " ["+tasks.get(taskIndex).getStatusIcon()+"] " + tasks.get(taskIndex).getDescription());
                    tasks.remove(taskIndex);
                    break;
                case DONE:
                    taskIndex = Integer.parseInt(describe)-1;
                    tasks.get(taskIndex).markAsDone();
                    System.out.println(breakLine+"Nice! I've marked this task as done: \n" +
                            " ["+tasks.get(taskIndex).getStatusIcon()+"] " + tasks.get(taskIndex).getDescription());
                    break;
            }
            System.out.println("Now you have "+tasks.size()+" tasks in the list.\n"+breakLine);
        }

         //check the input is not 0, is not exceed the total tasks index
         catch(NullPointerException e){
             System.out.println("There are only "+tasksCount+" tasks, please enter the correct task number");
         }
         //check the 0 or negative
         catch(ArrayIndexOutOfBoundsException e){
             System.out.println("There are only "+tasksCount+" tasks, please enter the correct task number");
         }
        catch(IndexOutOfBoundsException e){
            System.out.println("Task number entered is invalid");
        }
    }

    public static boolean contains(String test) {

        for (Status c : Status.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }


    public static void printList(){
        String[] output = new String[10];
        //println is print with extra \n, print is purely printout.
        System.out.print(breakLine);
        System.out.println("Here are the tasks in your list:");
        //print out all items in list
        for(int i = 0; i< tasks.size();i++){
            output = tasks.get(i).toString().split("\n ");
            System.out.println(i+1 + "."+output[1]);
        }
        System.out.println(breakLine);
    }

    public static void updateList(String taskIsDone){
        for(int i = 0; i< tasks.size();i++){
            if (tasks.get(i).getDescription().contains(taskIsDone)) {
                tasks.get(i).markAsDone();
            }
        }
    }


    public static void main(String[] args) {
        greeting();
        getResponse();
    }
}
